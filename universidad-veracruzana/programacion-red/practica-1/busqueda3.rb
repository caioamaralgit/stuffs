require "net/http"
require "json"

$base = '/maps/api/geocode/json'

def geocode(direccion)
    ruta = $base + '?address=' + URI.escape(direccion) + '&sensor=false'
    respuesta_cruda = Net::HTTP.get('maps.google.com', ruta)
    respuesta = JSON.load(respuesta_cruda)
    respuesta['results'][0]['geometry']['location']
end

puts geocode('pamir 10, xalapa, veracruz')