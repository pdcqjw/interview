# -*- coding: utf-8 -*-
"""
    __init__.py
    ~~~~~~~~~~~~~~

    Model user.

    :copyright: (c) 2016 by fengweimin.
    :date: 16/6/11
"""

from datetime import datetime

from flask_login import UserMixin
from flask_principal import RoleNeed, UserNeed
from werkzeug.utils import cached_property

from app.extensions import mdb
from app.mongosupport import Model, IN


class UserRole(object):
    """
    用户角色.
    """
    MEMBER = 1
    ADMIN = 9


class UserStatus(object):
    """
    用户状态.
    """
    NORMAL = u'normal'
    REJECTED = u'rejected'


@mdb.register
class User(Model, UserMixin):
    __collection__ = 'users'
    structure = {
        'name': unicode,
        'email': unicode,
        'password': unicode,
        'head': unicode,
        'point': int,
        'status': IN(UserStatus.NORMAL, UserStatus.REJECTED),
        'roles': [int],
        'createTime': datetime,
        'updateTime': datetime
    }
    required_fields = ['name', 'email', 'password', 'point', 'status', 'roles', 'createTime']
    default_values = {'point': 0, 'status': UserStatus.NORMAL, 'roles': [UserRole.MEMBER], 'createTime': datetime.now}
    indexes = [{'fields': ['email'], 'unique': True}]

    @cached_property
    def provides(self):
        """
        Provide user's identity
        """
        needs = [RoleNeed('authenticated'),
                 UserNeed(self._id)]

        if self.is_admin:
            needs.append(RoleNeed('admin'))

        return needs

    @cached_property
    def is_admin(self):
        return UserRole.ADMIN in self.roles

    @cached_property
    def is_rejected(self):
        return self.status == UserStatus.REJECTED

    def get_id(self):
        """
        UserMixin of flask-login.
        """
        return str(self._id)

    def __eq__(self, other):
        return self._id == other._id
