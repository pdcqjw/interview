import requests
import time

TRY_LIMIT = 10
SLEEP_INTERVAL = 120
PROXY = {"https": "http://127.0.0.1:9527", "http": "http://127.0.0.1:9527"}


def try_request(url):
    seconds_sleep = SLEEP_INTERVAL
    try_count = 0

    while try_count < TRY_LIMIT:
        try:
            proxies = PROXY
            response = requests.get(url, proxies=proxies)
            if response.status_code != 200:
                raise Exception('Failed to do request for url %s' % url)
            return response
        except Exception as e:
            print(e)
            try_count = try_count + 1
            time.sleep(seconds_sleep)