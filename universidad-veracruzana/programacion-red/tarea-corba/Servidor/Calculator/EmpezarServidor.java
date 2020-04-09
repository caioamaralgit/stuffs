import Calculator.*; 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
 
public class EmpezarServidor {
 
  public static void main(String args[]) {
    String[] param = { "-ORBInitialPort", "1050", "-ORBInitialHost", "localhost" };

    try {
      ORB orb = ORB.init(param, null);      
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
 
      OperationsObj operations = new OperationsObj();
      operations.setORB(orb); 
 
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(operations);
      Operations href = OperationsHelper.narrow(ref);
 
      org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
      NameComponent path[] = ncRef.to_name( "ABC" );
      ncRef.rebind(path, href);
 
      System.out.println("Calculadora lista para servir ...");
 
      for (;;) orb.run();
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
 
    System.out.println("Cerrando servidor ..."); 
  }
}