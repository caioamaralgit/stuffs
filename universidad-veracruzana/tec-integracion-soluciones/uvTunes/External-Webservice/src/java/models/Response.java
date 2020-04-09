package models;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

public class Response {
    @Expose private boolean exito = false;
    @Expose private ArrayList<Producto> productos = null;
    @Expose private String error = null;
    private ConnectionDB mysql;
    private Proveedor proveedor = null;
    
    // Consultar estoque
    public Response(String credencial) {
        try {
            this.mysql = new ConnectionDB();
            if (mysql.getMySQLConnection()) {        
                proveedor = new Proveedor(credencial);
                
                if (proveedor.valido) {      
                    exito = true;
                    productos = new ArrayList<Producto>();
                    
                    ArrayList<ArrayList<String>> result = mysql.executeSelect("SELECT id_producto_proveedor, nombre, fisico FROM producto WHERE permitir_consulta = true AND id_proveedor = " + proveedor.id);
                    result.forEach(producto -> productos.add(new Producto(producto)));
                }
            }

            mysql.closeConnection();
        } catch (NullPointerException ex) {
            System.out.println("Null ex -> " + ex);
            this.error = "NullPointerException :'(";
        } 
    }
    
    // Hacer sugerencia
    public Response(String credencial, String sugerencia) {
        try {
            mysql = new ConnectionDB();
            if (mysql.getMySQLConnection()) {
                proveedor = new Proveedor(credencial);

                if (proveedor.valido) exito = mysql.executeInsert("INSERT INTO sugerencia (id_proveedor, sugerencia) VALUES (" + proveedor.id + ", '" + sugerencia + "')");                
            }

            mysql.closeConnection();
        } catch (NullPointerException ex) {
            System.out.println("Null ex -> " + ex);
            this.error = "NullPointerException :'(";
        } 
    }
    
    public class Producto {
        @Expose int id;
        @Expose String nombre;
        @Expose int cantidad;

        public Producto(ArrayList<String> infoProducto) {
            this.id = Integer.parseInt(infoProducto.get(0));
            this.nombre = infoProducto.get(1);
            this.cantidad = Integer.parseInt(infoProducto.get(2));
        }
    }
    
    public class Proveedor {
        int id = 0;
        boolean valido = false;
        String credencial = null;
        
        public Proveedor(String credencial) {
            this.credencial = credencial;
            this.buscarProveedor();
        }
        
        private void buscarProveedor() {           
            ArrayList<ArrayList<String>> result = mysql.executeSelect("SELECT COUNT(*) AS total, id_proveedor FROM proveedor WHERE credencial = '" + this.credencial + "'");

            if (result != null && Integer.parseInt(result.get(0).get(0)) > 0) {
                this.valido = true;
                this.id = Integer.parseInt(result.get(0).get(1));
            }
        }  
    }
}