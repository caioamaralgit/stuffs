require "socket"

Socket.tcp_server_loop(4481) do |connection|
    #La forma más simple de leer datos de una conexión
    puts connection.read

    # Cerrar la conexión una vez terminada la lectura.
    # Hacer saber al cliente que puede dejar de esperar por nosotros.
    connection.close
end