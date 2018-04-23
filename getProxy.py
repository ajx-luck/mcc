# coding=utf-8

import requests
import re
from bs4 import BeautifulSoup as bs
import queue
import threading
from urllib import request


class proxyPick(threading.Thread):
    def __init__(self, q, name):
        threading.Thread.__init__(self)
        self._q = q
        self._name = name

    def run(self):
        while not self._q.empty():
            try:
                url = self._q.get()
                proxy_spider(url)
            except Exception:
                pass


def proxy_spider(url):
    headers = {'Host': 'baidu.com',
               'Connection': 'keep-alive',
               'Cache-Control': 'max-age=0',
               'Accept': 'text/html, */*; q=0.01',
               'X-Requested-With': 'XMLHttpRequest',
               'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36',
               'DNT': '1',
               'Referer': 'http://example.com/',
               'Accept-Encoding': 'gzip, deflate, sdch',
               'Accept-Language': 'zh-CN,zh;q=0.8,ja;q=0.6'
    }
    proxy_support = request.ProxyHandler({'http': '223.241.79.58:808'})
    # 创建Opener
    opener = request.build_opener(proxy_support)
    # 添加User Angent
    opener.addheaders = headers
    # r = requests.get(url=url, headers=headers)
    response = opener.open(url)
    # 读取相应信息并解码
    html = response.read().decode("utf-8")
    # soup = bs(r.content, "html.parser")
    soup = bs(html, "html.parser")
    data = soup.find_all(name='tr', attrs={'class': re.compile('|[^odd]')})

    for i in data:

        soup = bs(str(i), 'html.parser')
        data2 = soup.find_all(name='td')
        ip = str(data2[1].string)
        port = str(data2[2].string)
        types = str(data2[5].string).lower()

        proxy = {}
        proxy[types] = '%s:%s' % (ip, port)
        try:
            proxy_check(proxy, ip)
        except Exception:
            pass


def proxy_check(proxy, ip):
    # url = 'http://1212.ip138.com/ic.asp'
    # r = requests.get(url=url, proxies=proxy, timeout=6)

    f = open('D:/proxy/ip_proxy.txt', 'a+')
    f.write('%s' % proxy + '\n')
    print( '%s write down' %ip);
    # soup = bs(r.text, 'html.parser')
    # data = soup.find_all(name='center')
    # for i in data:
    #     a = re.findall(r'\[(.*?)\]', i.string)
    #     if a[0] == ip:
    #         # print proxy
    #         f.write('%s' % proxy + '\n')
    #         print
    #

    f.close()


# proxy_spider()

def main():
    q = queue.Queue()
    for i in range(201, 300):
        q.put('http://www.xicidaili.com/nn/' + str(i))

    threads = []
    thread_count = 10

    for i in range(thread_count):
        spider = proxyPick(q,str(i))
        threads.append(spider)

    for i in threads:
        i.start()

    for i in threads:
        i.join()

    print
    "It's down,sir!"


if __name__ == '__main__':
    f = open('D:/proxy/ip_proxy.txt', 'w')
    f.close()
    main()