class Productor extends Thread {
    private ColaSincronizada colaSinc;
    private int elementoMin, elementoMax; // Valores mínimos y máximos de los elementos

    // El constructor toma una cola sincronizada y el min y número de elementos como parámetros
    public Productor(ColaSincronizada cola, int min, int no) {
        colaSinc = cola;
        elementoMin = min;
        elementoMax = min + no;
    }

    // run() desempeña la tarea del productor
    public void run() {
        // Ciclo para generar los elementos
        for (int elemento = elementoMin; elemento <= elementoMax; elemento++) {
            // Mostrar nombre del hilo y elemento
            System.out.println("Productor_: " + this.getName() + " Valor: " + elemento);
            // Insertar el elemento en cola
            colaSinc.insertarElemento(elemento);
        }        
    }
}