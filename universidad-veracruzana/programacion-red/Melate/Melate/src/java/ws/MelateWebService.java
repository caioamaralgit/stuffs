package ws;

import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;

// Nombre de la clase que será recibida por los clientes
@WebService(serviceName = "MelateWebService")
public class MelateWebService {
    // Crea un objecto que generar numeros aleatórios
    Random randomGenerator = new Random();
    
    /* Nombre de la función que será recibida por los clientes (en general es el mismo nombre que 
     * se ocupa en el servidor) */
    @WebMethod(operationName = "generarNumero")
    public int gerenerarNumero() {
        // Genera un numero entre 0 y 41, lo agregas 1 y lo retorna al usuario
        return (1 + randomGenerator.nextInt(42));
    }
}
