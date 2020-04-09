class ColaSincronizada {
    private int posNodoInicial = 0, posNodoFinal = 0;
    private int numeroDeElementos = 0;
    private int[] elementos ;
    private int maxNumeroDeElementos;

    // Constructor
    public ColaSincronizada(int tMaximo) {
        maxNumeroDeElementos = tMaximo;
        elementos = new int[maxNumeroDeElementos];
    }

    // Número de elementos en la cola
    public int longitud() {
        return numeroDeElementos;
    }

    public synchronized void insertarElemento(int elemento) {
        // Verificar espacio disponible
        while (numeroDeElementos == maxNumeroDeElementos) {
            try {
                // Esperar por consumidores para liberar espacio
                wait();
            } catch (InterruptedException ex) {};
        }

        // Insertar elemento en la posición final (posNodoFinal)
        elementos[posNodoFinal] = elemento;
        // Mover el índice de posNodoFinal un lugar
        posNodoFinal = (posNodoFinal + 1) % maxNumeroDeElementos;
        numeroDeElementos += 1;
        // Notificar a los otros hilos en espera
        notifyAll();
    }

    public synchronized int removerElemento() {
        int elemento;
        // Esperar si la cola está vacía
        while (numeroDeElementos == 0) {
            try {
                wait();
            } catch (InterruptedException ex) { };
        }

        // Obtener el elemento en el índice posNodoInicial
        elemento = elementos[posNodoInicial];
        // Mover el índice un lugar
        posNodoInicial = (posNodoInicial + 1) % maxNumeroDeElementos;
        // Decrementar el número de elementos
        numeroDeElementos -= 1;
        // Notificar a todos los hilos que se ha liberado un espacio
        notifyAll();
        return elemento;
    }
}