require 'geocoder'

direccion = 'pamir 10, xalapa, veracruz'
resultados = Geocoder.search(direccion)

p resultados[0].coordinates