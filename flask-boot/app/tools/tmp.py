from string import Template
# from tools.urllib import try_request
import requests
import json


import requests
import time

TRY_LIMIT = 3
SLEEP_INTERVAL = 60
PROXY = {"https": "http://127.0.0.1:9527", "http": "http://127.0.0.1:9527"}


def try_request(url):
    seconds_sleep = SLEEP_INTERVAL
    try_count = 0

    while try_count < TRY_LIMIT:
        try:
            proxies = PROXY
            response = requests.get(url, proxies=proxies)
            if response.status_code != 200:
                raise Exception('Failed to do request for url %s' % url)
            return response
        except Exception as e:
            print(e)
            try_count = try_count + 1
            time.sleep(seconds_sleep)

# get coin count
def update_coin_ticker(app):
    """
    Update coin ticker
    """
    mdb.db.tickers.remove({})

    # get coin count
    total_coin_count = 0
    url = 'https://api.coinmarketcap.com/v2/global/'
    response = try_request(url)
    if response.status_code == 200:
        data = response.text
        data = json.loads(data)
        data = data['data']
        total_coin_count = data['active_cryptocurrencies']

    url_template = Template('https://api.coinmarketcap.com/v2/ticker/?start=$start_num&limit=$limit')
    # url = 'https://api.coinmarketcap.com/v2/ticker/?start=101&limit=10'
    cursor = 1
    page_count = 100
    while cursor < total_coin_count:
        params = {
            'start_num': cursor,
            'limit': page_count
        }
        url = url_template.substitute(params)
        response = try_request(url)
        if response.status_code == 200:
            data = response.text
            data = json.loads(data)
            data = data['data']

            store_rows = []
            for k, v in data.iteritems():
                store_rows.append(
                    {
                        'id': v['id'],
                        'name': v['name'],
                        'symbol': v['symbol'],
                        'rank': v['rank'],
                        'circulatingSupply': v['circulating_supply'],
                        'totalSupply': v['total_supply'],
                        'maxSupply': v['max_supply'],
                        'quotes': {
                            'USD': {
                                'price': v['quotes']['USD']['price'],
                                'volume24h': v['quotes']['USD']['price'],
                                'marketCap': v['quotes']['USD']['price'],
                                'percentChange1h': v['quotes']['USD']['price'],
                                'percentChange24h': v['quotes']['USD']['price'],
                                'percentChange7d': v['quotes']['USD']['price']
                            }
                        }
                    }
                )
            Ticker.insert_many(store_rows)
        cursor = cursor + page_count