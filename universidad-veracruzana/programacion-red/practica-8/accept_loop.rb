require "socket"

# Crear el socket que escucha 
server = TCPServer.new(4481)

# Loop infinito para aceptar y
# manejar conexiones
Socket.accept_loop(server) do |connection|
    # Manejar conexiones
    connection.close
end