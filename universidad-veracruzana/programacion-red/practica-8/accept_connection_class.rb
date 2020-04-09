require "socket"

# Crear el socket del servidor
server = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
server.bind(addr)
server.listen(Socket::SOMAXCONN)

# Aceptar una conexión nueva
connection, _ = server.accept

print 'Clase de la conexión: '
p connection.class 

print 'No. de descriptor de archivo del servidor: '
p server.fileno

print 'No. de descriptor de archivo de la conexión: '
p connection.fileno

print 'Dirección local: '
p connection.local_address

print 'Dirección remota: '
p connection.remote_address