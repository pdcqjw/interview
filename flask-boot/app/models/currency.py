from datetime import datetime

from app.extensions import mdb
from app.mongosupport import Model


@mdb.register
class Currency(Model):
    __collection__ = 'currencies'
    
    structure = {
        'id': unicode,
        'name': unicode,
        'symbol': unicode,
        'websiteSlug': unicode,
        'historyUpdateDate': datetime,
        'lastUpdated': datetime,
        'createTime': datetime
    }

    required_fields = ['id']
    default_values = {'createTime': datetime.now}
    indexes = [{'fields': ['id']}]
