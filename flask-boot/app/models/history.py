from datetime import datetime

from app.extensions import mdb
from app.mongosupport import Model


@mdb.register
class History(Model):
    __collection__ = 'histories'

    structure = {
        'symbol': unicode,
        'date': datetime,
        'open': float,
        'high': float,
        'low': float,
        'close': float,
        'volume': float,
        'marketCap': float,
        'average': float,
        'lastUpdated': datetime,
        'createTime': datetime
    }

    required_fields = ['symbol']
    default_values = {'createTime': datetime.now}
    indexes = [{'fields': ['date']}]
