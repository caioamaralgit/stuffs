package Calculator;

/**
* Calculator/OperationsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calculator.idl
* Wednesday, April 25, 2018 12:45:05 PM CDT
*/

public final class OperationsHolder implements org.omg.CORBA.portable.Streamable
{
  public Calculator.Operations value = null;

  public OperationsHolder ()
  {
  }

  public OperationsHolder (Calculator.Operations initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Calculator.OperationsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Calculator.OperationsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Calculator.OperationsHelper.type ();
  }

}
