// Crea un número de productores (numProductores)
// y consumidores (numConsumidores) y una cola sincronizada.
// Inician su trabajo y eventualmente termina el proceso
// al insertar 1 elemento en la cola "numConsumidores" de veces.

class MultiProdCons {
    public static void main(String[] args) {
        int numConsumidores = 3, numProductores = 2;

        // Se crea una cola de 5 elementos
        ColaSincronizada colaSincronizada = new ColaSincronizada(5);

        // Se crean los consumidores y productores.
        Consumidor[] consumidores = new Consumidor[numConsumidores];
        Productor[] productores = new Productor[numProductores];

        // Iniciar los consumidores
        for (int i = 0; i < numConsumidores; i += 1) {
            consumidores[i] = new Consumidor(colaSincronizada);
            consumidores[i].start();
        }

        // Iniciar los productores
        for (int i = 0; i < numProductores; i += 1) {
            productores[i] = new Productor(colaSincronizada, i * 100, 50);
            productores[i].start();
        }

        // Esperar que los productores terminen
        for (int i = 0; i < numProductores; i += 1) {
            try {
                productores[i].join();
            } catch (InterruptedException ex) {};
        }

        // Insertar -1 por cada consumidor que termine
        for (int i = 0; i < numConsumidores; i += 1) { 
            colaSincronizada.insertarElemento(-1);
        }

        // Esperar que los consumidores terminen
        for (int i = 0; i < numConsumidores; i += 1) {
            try {
                consumidores[i].join();
            } catch (InterruptedException ex) {};
        }

        System.out.println("Completado con éxito");
    }
}