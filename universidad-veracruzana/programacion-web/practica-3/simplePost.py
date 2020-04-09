import urllib.request as rq
import urllib.parse
import sys
from urllib.error import HTTPError

url = 'http://xl666.pythonanywhere.com/registro/'
headers = {'User-Agent' : 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36'}

data = { 'nombre' : sys.argv[1], 'nick' : sys.argv[2], 'pass' : sys.argv[3] }
data = urllib.parse.urlencode(data)
data = data.encode('utf-8')

request = rq.Request(url, data=data, headers=headers)

try:
    response = rq.urlopen(request)
    contenido = response.read().decode('utf-8')
    print(contenido)
except HTTPError as error:
    print('Hubo un error %s' % error.code)