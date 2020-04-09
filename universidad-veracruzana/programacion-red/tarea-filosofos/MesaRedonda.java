class MesaRedonda {

    public static void main(String[] args) {
        System.out.println("La mesa esta siendo puesta...");
        
        Tenedor tenedorA = new Tenedor();
        Tenedor tenedorB = new Tenedor();
        Tenedor tenedorC = new Tenedor();
        Tenedor tenedorD = new Tenedor();
        Tenedor tenedorE = new Tenedor();
        
        System.out.println("Los tenedores fueran puestos en la mesa.");

        Filosofo filoso1 = new Filosofo(tenedorA, tenedorB);
        Filosofo filoso2 = new Filosofo(tenedorB, tenedorC);
        Filosofo filoso3 = new Filosofo(tenedorC, tenedorD);
        Filosofo filoso4 = new Filosofo(tenedorD, tenedorE);
        Filosofo filoso5 = new Filosofo(tenedorE, tenedorA);

        filoso1.start();
        filoso2.start();
        filoso3.start();
        filoso4.start();
        filoso5.start();
    }
}