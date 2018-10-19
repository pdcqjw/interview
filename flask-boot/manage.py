# -*- coding: utf-8 -*-
"""
    manage.py
    ~~~~~~~~~~~~~~

    Manage scripts.

    :copyright: (c) 2016 by fengweimin.
    :date: 16/6/11
"""

from flask_script import Server, Shell, Manager

from app import create_app

app = create_app()
manager = Manager(app)

manager.add_command('runserver', Server('0.0.0.0', port=6060))


def _make_context():
    return dict(app=app)


manager.add_command('shell', Shell(make_context=_make_context))


@manager.command
def init_history_data():
    from app.tools.coinmarketcap_usd_history import create_all_coin_history
    create_all_coin_history()


@manager.command
def update_coin_ticker():
    from app.jobs import update_coin_ticker
    update_coin_ticker(None)


@manager.command
def count_history_symbol():
    from app.models import History
    tmp = len(History.find({}).distinct('symbol'))
    print(tmp)


if __name__ == '__main__':
    manager.run()
