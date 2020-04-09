require "socket"

# Recibe clientes de tanto IPv4 cuanto IPv6
server = Socket.tcp_server_sockets(4481)