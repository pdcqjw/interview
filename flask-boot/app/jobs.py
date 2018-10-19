# -*- coding: utf-8 -*-
"""
    jobs
    ~~~~~~~~~~~~~~

    Jobs defined here.

    :copyright: (c) 2016 by fengweimin.
    :date: 16/8/12
"""

import os
import threading
import time
import requests
import json
from collections import Counter

from string import Template

import schedule

from app.extensions import mdb
from app.models import Post
from app.models import Ticker
from app.tools.urllib import try_request


post_view_times_counter = Counter()


def update_view_times(app):
    """
    Update view times for posts.
    """
    app.logger.info('Scheduler update_view_times running: %s' % post_view_times_counter)
    d = dict(post_view_times_counter)
    post_view_times_counter.clear()
    for k, v in d.iteritems():
        p = Post.find_one({'_id': k})
        if p:
            try:
                p.viewTimes += v
                p.save()
            except:
                app.logger.exception('Failed when updating the viewTime for album %s' % p._id)


def append_coin_history(app):
    pass


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
    app.logger.exception('There are %s coins need to be update' % total_coin_count)

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
        app.logger.exception('Coin ticker rank from %s to %s update successfully' % (cursor, cursor + page_count - 1))

        cursor = cursor + page_count


def run_schedule(app):
    """
    Invoke schedule.
    """
    # For schedule rules please refer to https://github.com/dbader/schedule
    schedule.every(20).minutes.do(update_view_times, app)
    schedule.every(180).minutes.do(update_coin_ticker, app)
    schedule.every(1).days.do(append_coin_history, app)

    while True:
        schedule.run_pending()
        time.sleep(1)


def init_schedule(app):
    """
    Init.
    """
    # http://stackoverflow.com/questions/9449101/how-to-stop-flask-from-initialising-twice-in-debug-mode/
    if not app.debug or os.environ.get('WERKZEUG_RUN_MAIN') == 'true':
        t = threading.Thread(target=run_schedule, args=(app,))
        # Python threads don't die when the main thread exits, unless they are daemon threads.
        t.setDaemon(True)
        t.start()
