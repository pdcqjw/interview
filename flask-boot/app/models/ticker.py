from datetime import datetime

from app.extensions import mdb
from app.mongosupport import Model


@mdb.register
class Ticker(Model):
    __collection__ = 'tickers'
    # structure = {
    #     'date': unicode,
    #     'open': float,
    #     'close': float,
    #     'lowest': float,
    #     'highest': float,
    #     'createTime': datetime
    # }

    structure = {
        'id': unicode,
        'name': unicode,
        'symbol': unicode,
        'rank': int,
        # 'priceUsd': float,
        # 'priceBtc': float,
        # 'volume24hUsd': float,
        # 'marketCapUsd': float,
        'circulatingSupply': float,
        'totalSupply': float,
        'maxSupply': float,
        # 'percentChange1h': float,
        # 'percentChange24h': float,
        # 'percentChange7d': float,
        'quotes': {
            'USD': {
                'price': float,
                'volume24h': float,
                'marketCap': float,
                'percentChange1h': float,
                'percentChange24h': float,
                'percentChange7d': float
            }
        },
        'lastUpdated': datetime,
        'createTime': datetime
    }

    required_fields = ['id', 'name', 'symbol']
    default_values = {'createTime': datetime.now}
    indexes = [{'fields': ['id'], 'unique': True}]
