class Empezar {
    static String[] regalo = { "", "" };

    public static void main(String[] args) {
        String[] nombres = { "Hilo 1", "Hilo 2", "Hilo 3" };
        String[] tiposRegalos = { "Chocolates", "Bombones", "Rosas" };
        boolean[] envidioso = { false, false, true };

        CambiarRegalos cola = new CambiarRegalos(nombres);

        Hilos hilo1 = new Hilos(nombres[0], tiposRegalos[0], envidioso[0], cola);
        Hilos hilo2 = new Hilos(nombres[1], tiposRegalos[1], envidioso[1], cola);
        Hilos hilo3 = new Hilos(nombres[2], tiposRegalos[2], envidioso[2], cola);

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}