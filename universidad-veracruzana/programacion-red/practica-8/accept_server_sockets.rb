require "socket"

# Crear el socket que escucha 
servers = Socket.tcp_server_sockets(4481)

# Loop infinito para aceptar y
# manejar conexiones
Socket.accept_loop(servers) do |connection|
    # Manejar conexiones
    connection.close
end