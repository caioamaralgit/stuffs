require "socket"

module CloudHash
    class Server 
        def initialize(port)
            # Crear el socket del servidor
            @server = TCPServer.new(port)
            puts "Listening on port #{@server.local_address.ip_port}"
            @storage = {}
        end

        def start
            # Crear el loop infinito
            Socket.accept_loop(@server) do |connection|
                handle(connection)
                connection.close
            end 
        end

        def handle(connection)
            # Leer de la conexión hasta encontrar EOF
            request = connection.read

            # Escribir de rereso el resultado de la operación hash
            connection.write process(request)
        end

        # Comandos soportados:
        # SET key value
        # GET key
        def process(request)
            command, key, value = request.split

            case command.upcase
            when 'GET'
                @storage[key]
            when 'SET'
                @storage[key] = value
            end
        end
    end
end

server = CloudHash::Server.new(4481)
server.start