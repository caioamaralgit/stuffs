package proveedores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import models.Response;

@Path("/proveedores")
public class Proveedores {
    private static Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET @Produces("application/json")
    public String consultarEstoque(@QueryParam("credencial") String credencial) {
        String credencialUsuario = "";
        
        try {
            if (credencial == null) credencial = "";

            credencialUsuario = limpiarTexto(credencial);
        } catch (NullPointerException ex) { }
        
        return json.toJson(new Response(credencialUsuario));
    }
    
    @POST @Produces("application/json")
    public String enviarSugerencia(@QueryParam("credencial") String credencial, @QueryParam("sugerencia") String sugerencia) {        
        String credencialUsuario = "";
        String sugerenciaUsuario = "";
        
        try {
            if (credencial == null || sugerencia == null) credencial = sugerencia = "";        

            credencialUsuario = limpiarTexto(credencial);
            sugerenciaUsuario = limpiarTexto(sugerencia);
        } catch (NullPointerException ex) { }
        
        return json.toJson(new Response(credencialUsuario, sugerenciaUsuario));
    }
    
    private String limpiarTexto(String texto) {
        return texto.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'").trim();
    }
}