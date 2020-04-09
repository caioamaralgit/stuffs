import Calculator.*; 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.*;
 
public class EmpezarCliente {
  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int numA, numB; 
    boolean continuar = true;
    String[] param = { "-ORBInitialPort", "1050", "-ORBInitialHost", "localhost" };

    try {
      ORB orb = ORB.init(param, null);
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
      Operations calc = (Operations) OperationsHelper.narrow(ncRef.resolve_str("ABC"));

      System.out.println("Bienvenido a la calculadora!\n");

      do {
        switch (preguntarOperacion()) {
          case 1: 
            System.out.println("\nOperación seleccionada: Suma\n");
            numA = capturarNumero(1);
            numB = capturarNumero(2);
            System.out.println("\nResultado recibido -> " + numA + " + " + numB + " = " + calc.suma(numA, numB));
            break;
          case 2: 
            System.out.println("\nOperación seleccionada: Resta\n");
            numA = capturarNumero(1);
            numB = capturarNumero(2);
            System.out.println("\nResultado recibido -> " + numA + " - " + numB + " = " + calc.resta(numA, numB));
            break;
          case 3: 
            System.out.println("\nOperación seleccionada: Multiplicación\n");
            numA = capturarNumero(1);
            numB = capturarNumero(2);
            System.out.println("\nResultado recibido -> " + numA + " x " + numB + " = " + calc.multiplicacion(numA, numB));
            break;
          case 4: 
            System.out.println("\nOperación seleccionada: División\n");
            numA = capturarNumero(1);
            numB = capturarNumero(2);
            System.out.println("\nResultado recibido -> " + numA + " / " + numB + " = " + calc.division(numA, numB));
            break;
          case 5: 
            System.out.println("\nOperación seleccionada: Potencia\n");
            numA = capturarNumero(1);
            numB = capturarNumero(2);
            System.out.println("\nResultado recibido -> " + numA + " ^ " + numB + " = " + calc.potencia(numA, numB));
            break;
          case 6: 
            System.out.println("\nOperación seleccionada: Raiz Cuadrada\n");
            numA = capturarNumero(1);
            System.out.println("\nResultado recibido -> √" + numA + " = " + calc.raizc(numA));
            break;
          default:
            System.out.println("Cerrando calculadora...");
            continuar = false;
          }

          System.out.println("\nPrima ENTER para continuar...\n\n");
          System.in.read();
          System.out.println(new String(new char[50]).replace("\0", "\r\n"));
      } while (continuar);
    } catch (Exception e) {
      System.out.println("Ocurrió un error: " + e);
      e.printStackTrace();
    } 
  }
  
  public static int preguntarOperacion() {
    int operacion = 0;
    boolean valido = false;

    do {
      System.out.println("**************************** ");
      System.out.println("* Operaciones possibles: \n* ");
      System.out.println("* 1 - Suma");
      System.out.println("* 2 - Resta");
      System.out.println("* 3 - Multiplicación");
      System.out.println("* 4 - División");
      System.out.println("* 5 - Potencia");
      System.out.println("* 6 - Raiz Cuadrada");
      System.out.println("* 0 - Salir \n* ");
      System.out.println("**************************** \n");

      System.out.print("Cual operación deseas hacer: ");
      
      try {
        operacion = Integer.parseInt(scan.nextLine());

        if (operacion < 0 || operacion > 6) System.out.println("Operación inválida!\n");
        else valido = true;
      } catch (Exception ex) {
        System.out.println("Número inválido!\n");
      }
    } while (!valido);

    return operacion;
  }

  public static int capturarNumero(int numero) {
    int numInserido = 0;
    boolean valido = false;

    do {
      System.out.print("Digite el " + (numero == 1 ? "primer" : "segun") + " número -> ");
      
      try {
        numInserido = Integer.parseInt(scan.nextLine());
        valido = true;
      } catch (Exception ex) {
        System.out.println("Número inválido!\n");
      }
    } while (!valido);

    return numInserido;
  }
}