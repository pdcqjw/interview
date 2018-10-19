DOMAIN = 'flask-boot.com'

DEBUG = True
SECRET_KEY = 'this is a secret'

CACHE_TYPE = "simple"
CACHE_DEFAULT_TIMEOUT = 300

ACCEPT_LANGUAGES = ['en', 'zh']

BABEL_DEFAULT_LOCALE = 'zh'
BABEL_DEFAULT_TIMEZONE = 'Asia/Shanghai'

DEBUG_LOG = 'logs/debug.log'
ERROR_LOG = 'logs/error.log'

ADMINS = ['vannisterooy2008@126.com']

MAIL_SERVER = 'smtp.flask-boot.com'
MAIL_PORT = 465
MAIL_USE_TLS = False
MAIL_USE_SSL = True
MAIL_USERNAME = 'support@flask-boot.com'
MAIL_PASSWORD = ''
MAIL_DEFAULT_SENDER = 'support@flask-boot.com'

MONGODB_DATABASE = 'demo'
MONGODB_HOST = 'localhost'
MONGODB_PORT = 27017
MONGODB_USERNAME = None
MONGODB_PASSWORD = None
