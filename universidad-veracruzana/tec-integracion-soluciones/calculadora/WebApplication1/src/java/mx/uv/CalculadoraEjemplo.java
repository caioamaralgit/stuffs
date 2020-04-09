/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uv;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alex
 */
@WebService(serviceName = "CalculadoraEjemplo")
public class CalculadoraEjemplo {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Suma")
    public int Suma(@WebParam(name = "p1") String p1, @WebParam(name = "p2") String p2) {
        //TODO write your implementation code here:
        int x = 0, y = 0;
        TypeTester tt = new TypeTester();
        if(tt.isInteger(p1) == true && tt.isInteger(p2) == true){
            x = Integer.parseInt(p1);
            y = Integer.parseInt(p2);
        }else{
            System.out.println("Error con los valores insertados");
        }
        int r = x + y;
        return r;
    }
    
    @WebMethod(operationName = "Resta")
    public int Resta(@WebParam(name = "p1") String p1, @WebParam(name = "p2") String p2) {
        //TODO write your implementation code here:
        int x = 0, y = 0;
        TypeTester tt = new TypeTester();
        if(tt.isInteger(p1) == true && tt.isInteger(p2) == true){
            x = Integer.parseInt(p1);
            y = Integer.parseInt(p2);
        }else{
            System.out.println("Error con los valores insertados");
        }
        int r = x - y;
        return r;
    }
    
    @WebMethod(operationName = "Multiplicacion")
    public int Multiplicacion(@WebParam(name = "p1") String p1, @WebParam(name = "p2") String p2) {
        //TODO write your implementation code here:
        int x = 0, y = 0;
        TypeTester tt = new TypeTester();
        if(tt.isInteger(p1) == true && tt.isInteger(p2) == true){
            x = Integer.parseInt(p1);
            y = Integer.parseInt(p2);
        }else{
            System.out.println("Error con los valores insertados");
        }
        int r = x * y;
        return r;
    }
    
    
    @WebMethod(operationName = "Exponenciacion")
    public double Exponenciacion(@WebParam(name = "base") String base, @WebParam(name = "exponente") String exponente) {
        //TODO write your implementation code here:
        int x = 0, y = 0;
        TypeTester tt = new TypeTester();
        if(tt.isInteger(base) == true && tt.isInteger(exponente) == true){
            x = Integer.parseInt(base);
            y = Integer.parseInt(exponente);
        }else{
            System.out.println("Error con los valores insertados");
        }
        double r = Math.pow(x, y);
        return r;
    }
    
    @WebMethod(operationName = "RaizCuadrada")
    public double Radical(@WebParam(name = "base") String base) {
        //TODO write your implementation code here:
        int x = 0;
        TypeTester tt = new TypeTester();
        if(tt.isInteger(base) == true){
            x = Integer.parseInt(base);
        }else{
            System.out.println("Error con los valores insertados");
        }
        double r = Math.sqrt(x);
        return r;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Division")
    public Double Division(@WebParam(name = "p1") String p1, @WebParam(name = "p2") String p2) {
        //TODO write your implementation code here:
        int x = 0, y = 0;
        TypeTester tt = new TypeTester();
        if(tt.isInteger(p1) == true && tt.isInteger(p2) == true){
            x = Integer.parseInt(p1);
            y = Integer.parseInt(p2);
        }else{
            System.out.println("Error con los valores insertados");
        }
        double r = Math.pow(x, y);
        return r;
    }
}
