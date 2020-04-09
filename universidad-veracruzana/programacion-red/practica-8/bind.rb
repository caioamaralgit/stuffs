require "socket"

# Primero, crear un socket TCP
socket = Socket.new(:INET, :STREAM)

# Crear una estructura para almacenar las dirección para escuchar
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')

# Vincularse a ella
socket.bind(addr)