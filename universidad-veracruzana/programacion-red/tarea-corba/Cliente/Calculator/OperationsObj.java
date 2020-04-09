import Calculator.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
 
class OperationsObj extends OperationsPOA {
  private ORB orb;
 
  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
 
  public int suma(int a, int b) {
    return a + b;
  }
 
  public int resta(int a, int b) {
    return a - b;
  }
 
  public int multiplicacion(int a, int b) {
    return a * b;
  }
 
  public float division(int a, int b) {
    return a / b;
  }
 
  public double potencia(int a, int b) {
    return Math.pow(a, b);
  }
 
  public double raizc(int a) {
    return Math.sqrt(a);
  }
 
  public void desligar() {
    orb.shutdown(false);
  }
}