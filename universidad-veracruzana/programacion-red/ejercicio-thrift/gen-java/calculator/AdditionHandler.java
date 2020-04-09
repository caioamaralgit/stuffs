package calculator;
import org.apache.thrift.TException;

public class AdditionHandler implements AdditionService.Iface {
	@Override
	 public int addition(int n1, int n2) throws TException {
	    System.out.println("Addition(" + n1 + "," + n2 + ")");
	    return n1 + n2;
	 }	
}