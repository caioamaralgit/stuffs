require 'socket'
require 'url'

direccion = 'pamir 10, xalapa, veracruz'

socket = TCPSocket.new('maps.google.com', 80)

texto_sulicitud = """\
GET /maps/api/geocode/json?address=%s&sensor=false HTTP/1.1\r\n\
Host: maps.google.com:80\r\n\
User-Agent: busqueda4.rb (Desarrollo de Sistemas en Red)\r\n\
Connection: close\r\n\
\r\n\
"""

solicitud = texto_solicitud % URI.escape(direccion)

socket.print(solicitud)
respuesta = socket.read

puts respuesta