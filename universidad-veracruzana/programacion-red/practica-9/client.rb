require "socket"

module CloudHash
    class Client
        class << self
            attr_accessor :host, :port
        end

        def self.get(key)
            request "GET #{key}"
        end

        def self.set(key, value)
            request "SET #{key} #{value}"
        end

        def self.request(string)
            # Crear una nueva conección por cada operación
            @client = TCPSocket.new(host, port)
            @client.write(string)

            # Enviar EOF después de escribir la respuesta
            @client.close_write

            # Leer hasta EOF obtener la respuesta
            @client.read
        end
    end
end

CloudHash::Client.host = 'localhost'
CloudHash::Client.port = 4481

puts CloudHash::Client.set 'prez', 'obama'
puts CloudHash::Client.get 'prez'
puts CloudHash::Client.get 'vp'