import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author caioaba
 */

@Path ("/calculadora")
public class Calculadora {
  @GET  
  @Path ("/suma")
  public String suma (@QueryParam("num1") int num1, @QueryParam("num2") int num2) {
      return String.valueOf(num1 + num2);
  }    
    
  @GET  
  @Path ("/resta")
  public String resta (@QueryParam("num1") int num1, @QueryParam("num2") int num2) {
      return String.valueOf(num1 - num2);
  }
  
  @GET  
  @Path ("/multiplicacion")
  public String multiplicacion (@QueryParam("num1") int num1, @QueryParam("num2") int num2) {
      return String.valueOf(num1 * num2);
  }
  
  @GET  
  @Path ("/division")
  public String division (@QueryParam("num1") int num1, @QueryParam("num2") int num2) {
      return String.valueOf(num1 / num2);
  }
  
  @GET  
  @Path ("/potencia")
  public String potencia (@QueryParam("num1") int num1, @QueryParam("num2") int num2) {
      return String.valueOf(num1 ^ num2);
  }
  
  @GET  
  @Path ("/raizc")
  public String raizc (@QueryParam("num1") int num) {
      return String.valueOf(Math.sqrt(num));
  }
}
