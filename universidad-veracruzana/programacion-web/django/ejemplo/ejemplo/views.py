from django.http import HttpResponse

def holaMundo(request):
  contenido = """
    <html>
      <body>
        <h1>Hola mundo</h1>
      </body>
    </html> """

  return HttpResponse(contenido)