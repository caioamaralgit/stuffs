import re
import urllib.request as rq
from urllib.error import HTTPError

palabra = input("Cual palabra desea buscar? ")
headers = {'User-Agent' : 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36'}
url = 'http://www.wordreference.com/definicion/' + palabra
request = rq.Request(url, headers=headers)

try:
    response =  rq.urlopen(request)
    contenido = response.read().decode('utf-8')
    with open('definicion/' + palabra + '.html', 'w') as archivo:
        archivo.write(contenido)
    print('Definición salva.')

    patron = r'<h3>\s*%s.*</h3>.*<ol .*>(.*)</ol>[<ol .*>.*</ol>]*' % palabra
    com = re.compile(patron, re.DOTALL)
    m = com.search(contenido)
    
except HTTPError as error:
    print('Error, la petición no se puede satisfacer.')