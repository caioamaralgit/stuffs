require "socket"

client = TCPSocket.new('localhost', 4481)
client.write('gekko')

# Envia la señal de EOF para el servidor
client.close 