package Consumer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

public class SolicitarNumero extends HttpServlet {
    // Dice a Java donde buscar el Webservice, y la clase MelateWebService
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Melate/MelateWebService.wsdl")
    // Instancia un objecto de la clase del Webservice
    private MelateWebService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Informa que el tipo de contenido que el cliente va a recibir es un JSON, con codificacion UTF-8
        response.setContentType("text/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // "out" escribe algo para el usuario. Esa línea regresa un JSON con el numero generado
            out.println("{ \"resultado\": " + generarNumero() + " }");
        }
    }

    /* Los metodos doGet y doPost ya existen por patron en la clase HttpServlet y son ejecutados
     * cuando una peticion de ese tipo llega al objecto. Entonces Netbeans generó automaticamente
     * los dos metodos y los dos estan llamando el metodo processRequest, o sea, cualquier petición
     * GET o POST ejecutará el metodo processRequest */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // No sé.
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // Esta clase fue generada automaticamente y es la misma que hay en el servidor
    // Netbeans la creó para que el objecto de esa clase pueda ejecutar ese metodo
    private int generarNumero() {
        // Esa clase esta en la línea 13
        Consumer.MelateWebService port = service.getMelateWebServicePort();
        // Ejecutar ese metodo es lo mismo que ejecutar el metodo de la clase del servidor
        return port.generarNumero();
    }
}