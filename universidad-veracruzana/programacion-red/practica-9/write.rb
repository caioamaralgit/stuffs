require "socket"

Socket.tcp_server_loop(4481) do |connection|
    # La forma más simple de escribir datos a una conexión.
    connection.write("Bienvenidos!\n")
    connection.close
end