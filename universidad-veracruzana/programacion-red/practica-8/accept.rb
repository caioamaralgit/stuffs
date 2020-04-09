require "socket"

# Crear el socket del servidor
server = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
server.bind(addr)
server.listen(Socket::SOMAXCONN)

# Aceptar una conexión
connection, _ = server.accept