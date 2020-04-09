require "socket"

# Crear el socket del servidor
server = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
server.bind(addr)
server.listen(Socket::SOMAXCONN)

# Aceptar y manejar conexiones con un loop infinito
loop do
    connection, _ = server.accept
    # Manejar conexión
    connetion.close
end