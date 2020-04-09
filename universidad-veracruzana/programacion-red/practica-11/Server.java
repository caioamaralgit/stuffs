import java.io.IOException;
import java.net.*;
import java.text.RuleBasedCollator;

class Server {
    private static final int TTL = 3;
    private static final int PORT = 5000;
    private static final String GROUP = "225.1.5.7";
    
    public static void main(String[] args) {
        try {
            MulticastSocket multiSock = new MulticastSocket();
            multiSock.setTimeToLive(TTL);

            Thread enviar = new Thread(new Runnable() {        
                @Override
                public void run() {
                    int numero = 0;

                    while (true) {
                        try { 
                            numero++;
                            enviarPaquetes(multiSock, "SERVIDOR> " + numero + ": Me gustan los tacos");
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) { };
                    }
                }
            });

            Thread repasar = new Thread(new Runnable() {
                @Override
                public void run() {
                    String message;
                    byte[] buff = new byte[256];
                    DatagramPacket paquete;

                    try {
                        DatagramSocket dataSocket = new DatagramSocket(PORT);

                        while (true) {
                            buff = new byte[256];
                            paquete = new DatagramPacket(buff, buff.length);
                            dataSocket.receive(paquete);
                            message = new String(paquete.getData(), 0, paquete.getLength());
        
                            enviarPaquetes(multiSock, paquete.getAddress().toString() + " " + message);
                        }
                    } catch (IOException ex) { };
                }
            });
            
            enviar.start();
            repasar.start();
        } catch (IOException ex) { };
    }
    
    public static void enviarPaquetes(MulticastSocket multiSock, String message) {
        byte[] buff = new byte[256];

        try {
            DatagramPacket pack = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(GROUP), PORT);
            multiSock.send(pack);
    
            System.out.println(message);
        } catch (IOException ex) { };
    }
}