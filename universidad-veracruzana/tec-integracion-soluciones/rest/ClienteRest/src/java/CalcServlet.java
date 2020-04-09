/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author caioaba
 */
@WebServlet(urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {
    CalcWS ws = new CalcWS();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("Aqui");
            String result = "";
            
            switch(request.getParameter("operacion")) {
                case "suma": 
                    result = ws.suma(request.getParameter("num1"), request.getParameter("num2"));
                    break;
                case "resta": 
                    result = ws.resta(request.getParameter("num1"), request.getParameter("num2"));
                    break;
                case "multiplicacion": 
                    result = ws.multiplicacion(request.getParameter("num1"), request.getParameter("num2"));
                    break;
                case "division": 
                    result = ws.division(request.getParameter("num1"), request.getParameter("num2"));
                    break;
                case "potencia": 
                    result = ws.potencia(request.getParameter("num1"), request.getParameter("num2"));
                    break;
                case "raizc": 
                    result = ws.raizc(request.getParameter("num1"));
                    break;
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado</title>");            
            out.println("</head>");
            out.println("<body style='text-align: center;'>");
            out.println("<h1>Resultado de la Operación</h1>");
            out.println(result);
            out.println("<br/><button onclick=\"window.location.href = 'http://localhost:8080/ClienteRest/index.html';\"><< Regresar</button>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    static class CalcWS {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/ServicioWebRest/calcApp";

        public CalcWS() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("calculadora");
        }

        public String suma(String num1, String num2) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (num1 != null) {
                resource = resource.queryParam("num1", num1);
            }
            if (num2 != null) {
                resource = resource.queryParam("num2", num2);
            }
            resource = resource.path("suma");
            return resource.request().get(String.class);
        } 

        public String division(String num1, String num2) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (num1 != null) {
                resource = resource.queryParam("num1", num1);
            }
            if (num2 != null) {
                resource = resource.queryParam("num2", num2);
            }
            resource = resource.path("division");
            return resource.request().get(String.class);
        }

        public String multiplicacion(String num1, String num2) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (num1 != null) {
                resource = resource.queryParam("num1", num1);
            }
            if (num2 != null) {
                resource = resource.queryParam("num2", num2);
            }
            resource = resource.path("multiplicacion");
            return resource.request().get(String.class);
        }

        public String potencia(String num1, String num2) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (num1 != null) {
                resource = resource.queryParam("num1", num1);
            }
            if (num2 != null) {
                resource = resource.queryParam("num2", num2);
            }
            resource = resource.path("potencia");
            return resource.request().get(String.class);
        }

        public String raizc(String num1) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (num1 != null) {
                resource = resource.queryParam("num1", num1);
            }
            resource = resource.path("raizc");
            return resource.request().get(String.class);
        }

        public String resta(String num1, String num2) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (num1 != null) {
                resource = resource.queryParam("num1", num1);
            }
            if (num2 != null) {
                resource = resource.queryParam("num2", num2);
            }
            resource = resource.path("resta");
            return resource.request().get(String.class);
        }

        public void close() {
            client.close();
        }
    }

}
