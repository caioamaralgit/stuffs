class Tenedor {
    private boolean enUso = false;

    public void usar() {
        this.enUso = true;
    }

    public void liberar() {
        this.enUso = false;
    }

    public boolean estaEnUso() {
        return enUso;
    }
}