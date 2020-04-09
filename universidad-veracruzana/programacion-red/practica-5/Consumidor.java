class Consumidor extends Thread {
    private ColaSincronizada colaSinc;

    // El constructor toma una cola sincronizada como parámetro
    public Consumidor(ColaSincronizada cola) {
        colaSinc = cola;
    }

    // run() desempeña la tarea del consumidor
    public void run() {
        int elemento = 0;
        do {
            // Obtener un elemento de la cola
            elemento = colaSinc.removerElemento();
            // Imprimir el nombre del hilo y el elemento
            System.out.println("Consumidor: " + this.getName() + " Valor: " + elemento);
        } while (elemento != -1); // Itera hasta que el valor del elemento obtenido es -1
    }
}