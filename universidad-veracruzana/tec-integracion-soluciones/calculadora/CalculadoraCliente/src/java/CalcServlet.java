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

/**
 *
 * @author caioaba
 */
@WebServlet(urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalcServlet at " + request.getContextPath() + "</h1>");
            switch (request.getParameter("operacion")) {
                case "suma":
                    out.println(suma(request.getParameter("param1"),request.getParameter("param2")));
                    break;
                case "resta":
                    out.println(resta(request.getParameter("param1"),request.getParameter("param2")));
                    break;
                case "multiplicacion":
                    out.println(multiplicacion(request.getParameter("param1"),request.getParameter("param2")));
                    break;
                case "division":
                    out.println(division(request.getParameter("param1"),request.getParameter("param2")));
                    break;
                case "exponenciacion":
                    out.println(exponenciacion(request.getParameter("param1"),request.getParameter("param2")));
                    break;
                case "raizc":
                    out.println(raizCuadrada(request.getParameter("param1")));
                    break;
                default:
                    out.println("Operación desconocida");
            }
      
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

    private static int suma(java.lang.String p1, java.lang.String p2) {
        mx.uv.CalculadoraEjemplo_Service service = new mx.uv.CalculadoraEjemplo_Service();
        mx.uv.CalculadoraEjemplo port = service.getCalculadoraEjemploPort();
        return port.suma(p1, p2);
    }

    private static int resta(java.lang.String p1, java.lang.String p2) {
        mx.uv.CalculadoraEjemplo_Service service = new mx.uv.CalculadoraEjemplo_Service();
        mx.uv.CalculadoraEjemplo port = service.getCalculadoraEjemploPort();
        return port.resta(p1, p2);
    }

    private static int multiplicacion(java.lang.String p1, java.lang.String p2) {
        mx.uv.CalculadoraEjemplo_Service service = new mx.uv.CalculadoraEjemplo_Service();
        mx.uv.CalculadoraEjemplo port = service.getCalculadoraEjemploPort();
        return port.multiplicacion(p1, p2);
    }

    private static Double division(java.lang.String p1, java.lang.String p2) {
        mx.uv.CalculadoraEjemplo_Service service = new mx.uv.CalculadoraEjemplo_Service();
        mx.uv.CalculadoraEjemplo port = service.getCalculadoraEjemploPort();
        return port.division(p1, p2);
    }

    private static double exponenciacion(java.lang.String base, java.lang.String exponente) {
        mx.uv.CalculadoraEjemplo_Service service = new mx.uv.CalculadoraEjemplo_Service();
        mx.uv.CalculadoraEjemplo port = service.getCalculadoraEjemploPort();
        return port.exponenciacion(base, exponente);
    }

    private static double raizCuadrada(java.lang.String base) {
        mx.uv.CalculadoraEjemplo_Service service = new mx.uv.CalculadoraEjemplo_Service();
        mx.uv.CalculadoraEjemplo port = service.getCalculadoraEjemploPort();
        return port.raizCuadrada(base);
    }

}
