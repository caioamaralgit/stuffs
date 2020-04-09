require 'net/http'
require 'sinatra'
require 'json'


#Metodo post para enviar información desde el cliente para que sea procesada en el servidor
post('/Suma'){
a=params[:txtNum1]
b=params[:txtNum2]

url = 'http://localhost:5000/Suma?a='+a+'&b='+b
uri = URI(url)
#Puede proporcionar una cadena para la ruta de solicitud o un URI del cual Net :: HTTP extraera la ruta de solicitud
response = Net::HTTP.get(uri)

return "<h2>#{response}</h2>"

}

post('/Resta'){
a=params[:txtNum1]
b=params[:txtNum2]

url = 'http://localhost:5000/Resta?a='+a+'&b='+b
uri = URI(url)
response = Net::HTTP.get(uri)

return "<h2>#{response}</h2>"

}

post('/Multiplicar'){
a=params[:txtNum1]
b=params[:txtNum2]

url = 'http://localhost:5000/Multiplicar?a='+a+'&b='+b
uri = URI(url)
response = Net::HTTP.get(uri)

return "<h2>#{response}</h2>"

}

post('/Dividir'){
a=params[:txtNum1]
b=params[:txtNum2]

url = 'http://localhost:5000/Dividir?a='+a+'&b='+b
uri = URI(url)
response = Net::HTTP.get(uri)

return "<h2>#{response}</h2>"

}
