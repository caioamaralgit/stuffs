import java.net.*;

public class MiDireccionIpLocal {
    public static void main (String[] args) {
        try {
            InetAddress direccion = InetAddress.getLocalHost();

            System.out.println("Mi dirección local es: ");
            System.out.println(direccion);
        } catch (UnknownHostException hdEx) {
            System.out.println("No fue posible obtener la dirección IP local");
        }
    }
}