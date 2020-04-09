require "socket"

# Create the server socket
server = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
server.bind(addr)
server.listen(Socket::SOMAXCONN)
connection, _ = server.accept

# Crear una copia de la conexión
copy = connection.dup

# Esto apagará la comunicación de tolas las copias
connection.shutdown

# Esto cerrará la conexión 