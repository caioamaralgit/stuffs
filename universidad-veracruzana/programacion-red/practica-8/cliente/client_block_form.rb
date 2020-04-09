require "socket"

Socket.tcp('google.com', 80) do |connection|
    connection.write "GET / HTTP/1.1\r\n"
    connection.close
end

# Omitiendo el bloque como argumento tiene
# el mismo comportamiento que TCPSocket.new()
client = Socket.tcp('google.com', 80)