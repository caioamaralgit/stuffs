import java.io.*;
import java.net.*;

class ClienteEmisor {
    private static final int PORT = 5000;
    private static final String GROUP = "225.1.5.7";

    public static void main(String[] args) {
        try {
            MulticastSocket multiSock = new MulticastSocket(PORT);
    
            multiSock.joinGroup(InetAddress.getByName(GROUP));
            System.out.println("* Conectado al servidor multicast en " + multiSock.getLocalSocketAddress());

            Thread escuchar = new Thread(new Runnable() {        
                @Override
                public void run() {
                    System.out.println("* Escuchando servidor");

                    String mensaje;
                    byte[] packetPart = new byte[256];

                    while (true) {
                        try {
                            packetPart = new byte[256];
                            DatagramPacket pack = new DatagramPacket(packetPart, packetPart.length);
                            multiSock.receive(pack);
        
                            mensaje = new String(pack.getData(), pack.getOffset(), pack.getLength());
                            System.out.println(mensaje);
                        } catch (IOException ex) { };
                    }      
                }
            });

            Thread enviar = new Thread(new Runnable() {        
                String mensaje;
                BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

                @Override
                public void run() {
                    do {
                        try { 
                            mensaje = entrada.readLine();
        
                            if (!mensaje.equals("BYE")) {
                                DatagramPacket pack = new DatagramPacket (mensaje.getBytes(), mensaje.getBytes().length, InetAddress.getByName(GROUP), PORT);
                                multiSock.send(pack);
                            }
                        } catch (IOException ex) { };
                    } while (!mensaje.equals("BYE"));    
                    
                    try {
                        multiSock.leaveGroup(InetAddress.getByName(GROUP));
                    } catch (IOException ex) { };
                }
            });

            escuchar.start();
            enviar.start();
        } catch (IOException ex) { }; 
    }
}