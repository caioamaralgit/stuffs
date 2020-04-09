require "socket"

one_kb = 1024 #bytes

Socket.tcp_server_loop(4481) do |connection|
    # Leer datos en bloques de 1 one_kb
    while data = connection.read(one_kb) do
        puts data
    end

    connection.close
end