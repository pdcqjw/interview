from datetime import datetime

from app.extensions import mdb
from app.mongosupport import Model


@mdb.register
class Price(Model):
    __collection__ = 'prices'
    structure = {
        'date': unicode,
        'open': float,
        'close': float,
        'lowest': float,
        'highest': float,
        'createTime': datetime
    }

    required_fields = ['date', 'open', 'close', 'lowest', 'highest', 'createTime']
    default_values = {'createTime': datetime.now}
    indexes = [{'fields': ['date']}]
