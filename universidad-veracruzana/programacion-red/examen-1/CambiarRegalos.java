class CambiarRegalos {
    String[] regalo = { "", "" };
    String[] nombres;
    String proximo;

    public CambiarRegalos(String[] nombres) {
        this.nombres = nombres;
        proximo = this.nombres[2];
    }

    public synchronized int crearRegalo(String tipoRegalo, int cantidadRegalos) {
        boolean regaloCreado = false;

        for (int i = 0; i < regalo.length; i++) {
            if (regalo[i].equals("") && !regaloCreado) {
                cantidadRegalos++;
                regalo[i] = tipoRegalo + " (" + cantidadRegalos + ")";
                regaloCreado = true;
            }
        }

        notifyAll();
        return cantidadRegalos;
    }

    public synchronized boolean abrirRegalo(String nombre) {
        System.out.println("\n" + nombre + " recebió un regalo! El regalo contiene: ");

        for (String objeto : regalo) {
            System.out.println(" * " + objeto);
        }

        proximo = (proximo.equals(nombres[2]) ? nombres[0] : proximo.equals(nombres[0]) ? nombres[1] : nombres[2]);

        regalo = new String[]{ "", "" };

        notifyAll();
        return true;
    }

    public synchronized boolean regaloListo(String tipoRegalo) {
        boolean listo = true;
        
        for (String objeto : regalo) {
            if (objeto.equals("") || objeto.split(" ")[0].equals(tipoRegalo)) listo = false;
        }

        return listo;
    }
}