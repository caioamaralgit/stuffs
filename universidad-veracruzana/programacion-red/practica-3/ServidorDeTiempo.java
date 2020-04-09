import java.net.*;
import java.io.*;
import java.util.Date;

public class ServidorDeTiempo {
    public static void main(String args[]) {
        ServerSocket servidor;
        final int PUERTO_DAYTIME = 13;
        Socket socket;

        try {
            servidor = new ServerSocket(PUERTO_DAYTIME);

            do {
                socket = servidor.accept();
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                Date hora_fecha = new Date();
                salida.println(hora_fecha);
                socket.close();
            } while (true);
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }
}