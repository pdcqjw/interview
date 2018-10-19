# -*- coding: utf-8 -*-
"""
    v2
    ~~~~~~~~~~~~~~
    version 2.0 api implementations.
    :copyright: (c) 2018 by lipeizhao.
    :date: 2018/6/15
"""

import datetime
import re
from base64 import b32encode
from os import urandom
import json

import arrow
import flask
import pymongo
import requests
from flask import Blueprint, current_app, render_template
from flask.json import jsonify
from flask_babel import gettext as _
from flask_restplus import Resource, Api, fields
from pymongo import errors

# from app.models import Market, Price, Currency, History, SliceType, Config, User, CurrencyUnit, MiniInfo, EarlyWarning


v1 = Blueprint('v1', __name__)
api = Api(v1, version='1.0', title='Rest Api')  # version只是用在Swagger文档页面中


# 相关设置, 请参考官方文档
# http://flask-restplus.readthedocs.io/en/stable/quickstart.html

@api.route('/fizzbuzz')
class HelloEndPoint(Resource):
    def get(self):
        result = []
        result_str = ''
        for i in range(200):
            if i % 3 == 0 or '3' in str(i):
                result.append('Fizz')
            elif i % 5 == 0 or '5' in str(i):
                result.append('Buzz')
            else:
                result.append(i)

        for i in result:
            result_str = result_str + str(i) + ' '
            # print('%s' % i)
        print(result_str)
        return {'result': result_str}
