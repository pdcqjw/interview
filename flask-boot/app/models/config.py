# -*- coding: utf-8 -*-
"""
    teammate
    ~~~~~~~~~~~~~~

    Model config.

    :copyright: (c) 2017 by fengweimin.
    :date: 2017/2/28
"""

from datetime import datetime

from app.extensions import mdb
from app.mongosupport import Model


@mdb.register
class Config(Model):
    __collection__ = 'configs'
    structure = {
        'name': unicode,
        'createTime': datetime
    }

    # 配置内容设置到动态的字段下
    # 注意字段值的读写暂时只能通过__getitem__或者__setitem__访问
    use_schemaless = True

    required_fields = ['name', 'createTime']
    default_values = {'createTime': datetime.now}
    indexes = [{'fields': ['name'], 'unique': True}]
