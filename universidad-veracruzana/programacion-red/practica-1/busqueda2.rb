require "requests/sugar"

def geocode(direccion)
    parametros = { address: direccion, sensor: 'false' }
    base = 'http://maps.googleapis.com/maps/api/geocode/json'
    solicitud = Requests.get(base, params: parametros)
    respuesta = solicitud.json
    respuesta['results'][0]['geometry']['location']
end

puts geocode('pamir 10, xalapa, veracruz')