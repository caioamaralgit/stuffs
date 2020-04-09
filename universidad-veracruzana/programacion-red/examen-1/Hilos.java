class Hilos extends Thread {
    private String tipoRegalo;
    private boolean envidioso;
    private CambiarRegalos cola;

    private int cantidadRegalos = 0;
    private boolean regaloRecibido = false;

    public Hilos(String nombre, String tipoRegalo, boolean envidioso, CambiarRegalos cola) {
        this.setName(nombre);
        this.tipoRegalo = tipoRegalo;
        this.envidioso = envidioso;
        this.cola = cola;
    }

    public void run() {
        while (true) {
            if (!cola.proximo.equals(this.getName()) && (!this.envidioso || (this.envidioso && this.regaloRecibido))) {
                cantidadRegalos = cola.crearRegalo(tipoRegalo, cantidadRegalos);
            }

            try {
                while (!cola.regaloListo(this.tipoRegalo) || !cola.proximo.equals(this.getName())) {
                    sleep(3000);
                }

                regaloRecibido = cola.abrirRegalo(this.getName());                    

                if (this.envidioso && cantidadRegalos == 0) cantidadRegalos = cola.crearRegalo(tipoRegalo, cantidadRegalos);
            } catch (InterruptedException ie) {};
        }
    }
}