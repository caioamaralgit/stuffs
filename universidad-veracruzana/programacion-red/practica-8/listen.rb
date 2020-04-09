require "socket"

# Crear un socket vincularlo al puerto 4481
socket = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
socket.bind(addr)

# Escuchar las conexiones entrantes
socket.listen(5)