#!/usr/bin/python

"""
CoinMarketCap USD Price History

  Print the CoinMarketCap USD price history for a particular cryptocurrency in CSV format.
"""

import sys
import re
import urllib2
import time
import pymongo
import argparse
import datetime
import requests
import json

from app.models import History
from app.models import Currency
from app import mdb
from app.tools.urllib import try_request

REFRESH_COIN_LIST = False
REFRESH_COIN_HISTORY = False

parser = argparse.ArgumentParser()

parser.add_argument("currency", help="This is the name of the crypto, as is shown on coinmarketcap. For BTC, "
                                     "for example, type: bitcoin.", type=str)
parser.add_argument("start_date", help="Start date from which you wish to retrieve the historical data. For example, "
                                       "'2017-10-01'.", type=str)
parser.add_argument("end_date", help="End date for the historical data retrieval. If you wish to retrieve all the "
                                     "data then you can give a date in the future. Same format as in start_date "
                                     "'yyyy-mm-dd'.", type=str)
parser.add_argument("--dataframe", help="If present, returns a pandas DataFrame.", action='store_true')


def parse_options(args):
    """
    Extract parameters from command line.
    """

    currency = args.currency.lower()
    start_date = args.start_date
    end_date = args.end_date

    start_date_split = start_date.split('-')
    end_date_split = end_date.split('-')

    start_year = int(start_date_split[0])
    end_year = int(end_date_split[0])

    # String validation
    pattern = re.compile('[2][0][1][0-9]-[0-1][0-9]-[0-3][0-9]')
    if not re.match(pattern, start_date):
        raise ValueError('Invalid format for the start_date: ' + start_date + ". Should be of the form: yyyy-mm-dd.")
    if not re.match(pattern, end_date):
        raise ValueError('Invalid format for the end_date: ' + end_date + ". Should be of the form: yyyy-mm-dd.")
    # Datetime validation for the correctness of the date. Will throw a ValueError if not valid
    datetime.datetime(start_year, int(start_date_split[1]), int(start_date_split[2]))
    datetime.datetime(end_year, int(end_date_split[1]), int(end_date_split[2]))

    # CoinMarketCap's price data (at least for Bitcoin, presuambly for all others) only goes back to 2013
    invalid_args = start_year < 2013
    invalid_args = invalid_args or end_year < 2013
    invalid_args = invalid_args or end_year < start_year

    if invalid_args:
        print('Usage: ' + __file__ + ' <currency> <start_date> <end_date> --dataframe')
        sys.exit(1)

    start_date = start_date_split[0] + start_date_split[1] + start_date_split[2]
    end_date = end_date_split[0] + end_date_split[1] + end_date_split[2]

    return currency, start_date, end_date


def download_data(currency, start_date, end_date):
    """
    Download HTML price history for the specified cryptocurrency and time range from CoinMarketCap.
    """
    import urllib3
    url = 'https://coinmarketcap.com/currencies/' + currency + '/historical-data/' + '?start=' \
          + start_date + '&end=' + end_date
    html = None
    try:
        # url = 'https://api.coinmarketcap.com/v2/listings/'
        # proxies = {"https": "http://127.0.0.1:9527", "http": "http://127.0.0.1:9527"}
        # response = requests.get(url, proxies=proxies)
        # if response.status_code != 200:
        #     raise Exception('Failed to load page')
        response = try_request(url)
        html = response.content
        ####################

        # proxy = urllib2.ProxyHandler(proxies)
        # opener = urllib2.build_opener(proxy)
        # urllib2.install_opener(opener)
        # page = urllib2.urlopen(url, timeout=10)
        # if page.getcode() != 200:
        #     raise Exception('Failed to load page')
        # html2 = page.read()
        # page.close()

    except requests.exceptions.ProxyError as e:
        raise requests.exceptions.ProxyError
    except Exception as e:
        print('Error fetching price data from ' + url)
        print(
            'Did you use a valid CoinMarketCap currency?\nIt should be entered exactly as displayed on CoinMarketCap.com (case-insensitive), with dashes in place of spaces.')

        if hasattr(e, 'message'):
            print("Error message: " + e.message)
        else:
            print(e)
            sys.exit(1)

    return html


def extract_data(html):
    """
    Extract the price history from the HTML.

    The CoinMarketCap historical data page has just one HTML table.  This table contains the data we want.
    It's got one header row with the column names.

    We need to derive the "average" price for the provided data.
    """

    head = re.search(r'<thead>(.*)</thead>', html, re.DOTALL).group(1)
    header = re.findall(r'<th .*>([\w ]+)</th>', head)
    header.append('Average (High + Low / 2)')

    body = re.search(r'<tbody>(.*)</tbody>', html, re.DOTALL).group(1)
    raw_rows = re.findall(r'<tr[^>]*>' + r'\s*<td[^>]*>([^<]+)</td>' * 7 + r'\s*</tr>', body)

    # strip commas
    rows = []
    for row in raw_rows:
        row = [field.translate(None, ',') for field in row]
        rows.append(row)

    # calculate averages
    def append_average(row):
        high = float(row[header.index('High')])
        low = float(row[header.index('Low')])
        average = (high + low) / 2
        row.append('{:.2f}'.format(average))
        return row

    rows = [append_average(row) for row in rows]

    return header, rows


def render_csv_data(header, rows):
    """
    Render the data in CSV format.
    """
    print(','.join(header))

    for row in rows:
        print(','.join(row))


def clean_data(row):
    for idx, item in enumerate(row):
        if item in ['-']:
            row[idx] = None


def store_db(symbol, rows):
    """
    store data in database
    """

    datetime_format = '%b %d %Y'

    # clear data of this coin in database
    pass

    store_rows = []
    for row in rows:
        clean_data(row)

        date = datetime.datetime.strptime(row[0], datetime_format)
        open = float(row[1]) if row[1] else None
        high = float(row[2]) if row[2] else None
        low = float(row[3]) if row[3] else None
        close = float(row[4]) if row[4] else None
        volume = float(row[5]) if row[5] else None
        market_cap = float(row[6]) if row[6] else None
        average = float(row[7]) if row[7] else None
        print('%s %s' % (date, symbol))
        store_rows.append(
            {
                'date': date,
                'symbol': symbol,
                'open': open,
                'high': high,
                'low': low,
                'close': close,
                'volume': volume,
                'market_cap': market_cap,
                'average': average
            }
        )
    if store_rows:
        History.insert_many(store_rows)
        # currency = Currency.find_one({"symbol", symbol})
        mdb.db.currencies.update({"symbol": symbol}, {"$set": {"historyUpdateDate": datetime.datetime.now()}})
    else:
        return

# --------------------------------------------- Util Methods -----------------------------------------------------------


# def processDataFrame(df):
#     import pandas as pd
#     assert isinstance(df, pd.DataFrame), "df is not a pandas DataFrame."
#
#     cols = list(df.columns.values)
#     cols.remove('Date')
#     df.loc[:, 'Date'] = pd.to_datetime(df.Date)
#     for col in cols: df.loc[:, col] = df[col].apply(lambda x: float(x))
#     return df.sort_values(by='Date').reset_index(drop=True)


# def rowsFromFile(filename):
#     import csv
#     with open(filename, 'rb') as infile:
#         rows = csv.reader(infile, delimiter=',')
#         for row in rows:
#             print(row)


# ----------------------------------------------------------------------------------------------------------------------

# def try_request(url):
#     seconds_sleep = 60
#     try_count = 0
#     while try_count < 3:
#         try:
#             proxies = {"https": "http://127.0.0.1:9527", "http": "http://127.0.0.1:9527"}
#             response = requests.get(url, proxies=proxies)
#             if response.status_code != 200:
#                 raise Exception('Failed to load page')
#             return response
#         # except requests.exceptions.ProxyError:
#         except Exception as e:
#             print(e)
#             try_count = try_count + 1
#             time.sleep(seconds_sleep)


def coin_listing():
    if REFRESH_COIN_LIST:
        mdb.db.currencies.remove({})

        url = 'https://api.coinmarketcap.com/v2/listings/'
        response = try_request(url)
        if response.status_code == 200:
            data = response.text
            data = json.loads(data)
            print(data)
            data = data['data']
            coin_list = []
            for coin in data:
                coin_list.append(
                    {
                        'id': coin['id'],
                        'name': coin['name'],
                        'symbol': coin['symbol'],
                        'websiteSlug': coin['website_slug']
                    }
                )
            Currency.insert_many(coin_list)
        else:
            print('get coin list fail')

    coin_objects = Currency.find({})
    return coin_objects

# def try_coin_history_by_name(name, start_date, end_date):
#     name_list = [
#         name.lower().replace(' ', '-'),
#         name.lower().replace(' ', '')
#     ]
#
#     name_list.extend([item for item in name.lower().split(' ')])
#
#     for n in name_list:
#         html = download_data(n, start_date, end_date)
#         if html:
#             return html
#     return None


def create_one_coin_history(name, symbol):
    start_date = '20110101'
    end_date = datetime.datetime.now().strftime('%Y%m%d')

    cursor = History.find({"symbol": symbol}).sort([("date", pymongo.DESCENDING)])
    try:
        recent_history = cursor.next()
    except StopIteration:
        recent_history = None

    if recent_history:
        date_now = datetime.datetime.now()
        recent_history_date = recent_history.date
        latest_history_date = (date_now - datetime.timedelta(days=1)).replace(hour=0, minute=0, second=0, microsecond=0)
        if recent_history_date < latest_history_date:
            start_date = recent_history_date + datetime.timedelta(days=1)
            start_date = start_date.strftime('%Y%m%d')
        else:
            return
    else:
        pass

    html = None
    # try:
    #     html = download_data(name, start_date, end_date)
    # except:
    #     html = download_data(name_back, start_date, end_date)
    # html = try_coin_history_by_name(name, start_date, end_date)
    # try_count = 0
    # while try_count < 3:
    #     try:
    #         html = download_data(name, start_date, end_date)
    #     except requests.exceptions.ProxyError:
    #         print('try request fail, count %s' % try_count)
    #         try_count = try_count + 1
    #         time.sleep(5)

    html = download_data(name, start_date, end_date)
    header, rows = extract_data(html)
    store_db(symbol, rows)


def create_all_coin_history():
    # get coin list
    coin_objects = coin_listing()

    # clear history
    if REFRESH_COIN_HISTORY:
        mdb.db.histories.remove({})

    for coin in coin_objects:
        # symbol = coin['symbol']
        # name = coin['website_slug']
        now_date = datetime.datetime.now().date()

        if not coin.historyUpdateDate or not coin.historyUpdateDate.date() == now_date:
            symbol = coin.symbol
            name = coin.websiteSlug

            # if name not available, try name_back
            # name_back = coin['name'].lower().replace(' ', '')
            create_one_coin_history(name, symbol)


#
# def main(args=None):
#
#     tmp = float('-3999')
#     tmp2 = 0
#     # currency = 'bitcoin'
#     # start_date = '20180409'
#     # end_date = '20180509'
#
#     # assert that args is a list
#     if (args is not None):
#         args = parser.parse_args(args)
#     else:
#         args = parser.parse_args()
#
#     create_all_coin_history()
#
#     # currency, start_date, end_date = parse_options(args)
#     #
#     # html = download_data(currency, start_date, end_date)
#     #
#     # header, rows = extract_data(html)
#     #
#     # if (args.dataframe):
#     #     import pandas as pd
#     #     return processDataFrame(pd.DataFrame(data=rows, columns=header))
#     # else:
#     #     render_csv_data(header, rows)
#
#
# if __name__ == '__main__':
#     df = main()
