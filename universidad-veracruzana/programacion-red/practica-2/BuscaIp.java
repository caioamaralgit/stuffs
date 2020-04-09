import java.net.*;
import java.util.*;

public class BuscaIp {
    public static void main (String[] args) {
        String host;
        Scanner entrada = new Scanner(System.in);
        InetAddress direccionIp;

        System.out.println("\nIngrese el nombre de un host: ");
        host = entrada.next();

        try {
            direccionIp = InetAddress.getByName(host);
            System.out.println("Dirección IP: " + direccionIp.toString());
        } catch (UnknowsHostException ehDe) {
            System.out.println("No se pude encontrar al host " + host);
        }
    }
}