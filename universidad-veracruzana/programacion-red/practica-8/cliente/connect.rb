require "socket"

socket = Socket.new(:INET, :STREAM)

# Iniciar una conexión a google.com en el puerto 80
remote_addr = Socket.pack_sockaddr_in(70, 'google.com')
socket.connect(remote_addr)