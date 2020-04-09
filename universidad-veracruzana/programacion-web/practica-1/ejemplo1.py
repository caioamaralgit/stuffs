import urllib.request

#respuesta =  urllib.request.urlopen('https://www.uv.mx')

headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 6.1; Win64; x64)'}
url = 'http://www.wordreference.com/definicion/perro'
request = urllib.request.Request(url, headers=headers)
respuesta =  urllib.request.urlopen(request)

contenido = respuesta.read()
ar = open('prueba.html', 'w')
ar.write(contenido.decode('utf-8'))
ar.close()





