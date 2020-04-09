require "socket"

# Este socket se vinculará a la interface loopback y
# solo escuchará por cliente desde localhost.
local_socket = Socket.new(:INET, :STREAM)
local_addr = Socket.pack_sockaddr_in(4481, '127.0.0.1')
local_socket.bind(local_addr)

# Este socket se vinculará con culaquier interfaz bien
# conocida y estará escuchando por cualquier cliente 
# que pueda enrutar mensasjes a ella.
any_socket = Socket.new(:INET, :STREAM)
any_addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
any_socket.bind(any_addr)

# Este socket intentará vincularse con una interfaz 
# bien conocida y lanzará una exceptión Errno::EADDRNOTAVAIL.
error_socket = Socket.new(:INET, :STREAM)
error_addr = Socket.pack_sockaddr_in(4481, '1.2.3.4')
error_socket.bind(error_addr)