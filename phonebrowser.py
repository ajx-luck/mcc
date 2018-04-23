# coding=utf-8

import requests
import sys
import queue
import threading
from bs4 import BeautifulSoup as bs
import re
import time
from urllib import parse
from urllib import request

headers = { 'Host':'baidu.com',
                    'Connection':'keep-alive',
                    'Cache-Control':'max-age=0',
                    'Accept': 'text/html, */*; q=0.01',
                    'X-Requested-With': 'XMLHttpRequest',
                    'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36',
                    'DNT':'1',
                    'Referer': 'http://example.com/',
                    'Accept-Encoding': 'gzip, deflate, sdch',
                    'Accept-Language': 'zh-CN,zh;q=0.8,ja;q=0.6'
}

headers1 = [('Host','baidu.com'),('Connection','keep-alive'),( 'Cache-Control','max-age=0'),
            ('Accept','text/html, */*; q=0.01'),('X-Requested-With','XMLHttpRequest'),
            ( 'User-Agent','Mozilla/5.0 (iPhone; CPU iPhone OS 8_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12B411 Safari/600.1.4'),
            ('DNT','1'),('Accept-Language', 'zh-CN,zh;q=0.8,ja;q=0.6')
            ]

proxy = {'https': '223.241.118.173:8010'}

class baiduSpider(threading.Thread):
    def __init__(self, q, name):
        threading.Thread.__init__(self)
        self._q = q
        self._name = name

    def run(self):
        while not self._q.empty():
            url = self._q.get()
            try:
                self.getProxyUrl(url)
            except Exception as e :
                print(e)
                pass
                # 一定要异常处理！！！不然中途会停下，爬取的内容就不完整了！！！

    def get_url(self, url):
        r = requests.get(url=url, headers=headers)
        soup = bs(r.content, "html.parser")
        urls = soup.find_all(name='a', attrs={'href': re.compile(('.'))})
        #        for i in urls:
        #            print i

        # 抓取百度搜索结果中的a标签，其中href是包含了百度的跳转地址

        for i in urls:
            if 'www.baidu.com/link?url=' in i['href']:
                a = requests.get(url=i['href'], headers=headers)

                # 对跳转地址进行一次访问，返回访问的url就能得到我们需要抓取的url结果了

                # if a.status_code == 200:
                # print a.url

                with open('D:/url/' + self._name + '.txt') as f:
                    if a.url not in f.read():
                        f = open('D:/url/' + self._name + '.txt', 'a')
                        f.write(a.url + '\n')
                        f.close()

    def getProxyUrl(self,url):
        proxy_support = request.ProxyHandler(proxy)
        # 创建Opener
        opener = request.build_opener(proxy_support)
        # 添加User Angent
        opener.addheaders = headers1
        # r = requests.get(url=url, headers=headers)
        response = opener.open(url)
        # 读取相应信息并解码
        html = response.read().decode("utf-8")
        soup = bs(html, "html.parser")
        urls = soup.find_all(name='a', attrs={'href': re.compile(('.'))})
        #        for i in urls:
        #            print i

        # 抓取百度搜索结果中的a标签，其中href是包含了百度的跳转地址

        for i in urls:
            if 'www.baidu.com/link?url=' in i['href']:
                a = requests.get(url=i['href'], headers=headers)

                # 对跳转地址进行一次访问，返回访问的url就能得到我们需要抓取的url结果了

                # if a.status_code == 200:
                # print a.url

                with open('D:/url/' + self._name + '.txt') as f:
                    if a.url not in f.read():
                        f = open('D:/url/' + self._name + '.txt', 'a')
                        f.write(a.url + '\n')
                        f.close()
                time.sleep(1)


def readkeyword():
    with open("D:\\bd\\234.csv", "r", encoding="utf_16") as file:
        for line in file:
            rows = line.split('\t')
            keyword = rows[2];
            print(keyword)
            main(keyword)

def main(keyword):
    name = keyword

    f = open('D:/url/' + name + '.txt', 'w')
    f.close()

    q = queue.Queue()
    for i in range(0, 30, 10):
        q.put('http://www.baidu.com/s?wd=%s&pn=%s' % (parse.quote(keyword), str(i)))

    threads = []
    thread_count = 3

    for i in range(thread_count):
        spider = baiduSpider(q, name)
        threads.append(spider)

    for i in threads:
        i.start()

    for i in threads:
        i.join()

    print("It's down,sir!")



if __name__ == '__main__':
    readkeyword()