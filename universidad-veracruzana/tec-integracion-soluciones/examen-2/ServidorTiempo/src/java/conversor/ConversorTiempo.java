package conversor;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("servicio")
public class ConversorTiempo {
    @GET @Produces(MediaType.APPLICATION_JSON)
    public String convertTime(@QueryParam("monto") double monto, @QueryParam("uFuente") String uFuente, @QueryParam("uDestino") String uDestino) {        
        String error = "";
        double nuevoTiempo = 0;
        
        try {
            switch (uFuente) {
                case "segundo":
                    nuevoTiempo = convertFromSec(monto, uDestino);
                    break;
                case "minuto":
                    nuevoTiempo = convertFromMin(monto, uDestino);
                    break;
                case "hora":
                    nuevoTiempo = convertFromHour(monto, uDestino);
                    break;
                case "dia":
                    nuevoTiempo = convertFromDay(monto, uDestino);
                    break;
                case "semana":
                    nuevoTiempo = convertFromWeek(monto, uDestino);
                    break;
                default: // mes
                    nuevoTiempo = convertFromMonth(monto, uDestino);
            }
        } catch (NullPointerException ex) {
            error = "Faltan uno o más parámetros.";
        }
        
        return "{ \"resultado\" : " + nuevoTiempo + ", \"error\": \"" + error + "\" }";
    }
    
    private double convertFromSec(double monto, String uDestino) {
        double diferencia = (uDestino.equals("minuto") ? 0.016667 :
                             uDestino.equals("hora") ? 0.000277778 :
                             uDestino.equals("dia") ? 1.1574e-5 :
                             uDestino.equals("semana") ? 1.65345181878e-6 : 3.8052e-7);
        
        return monto * diferencia;
    }
    
    private double convertFromMin(double monto, String uDestino) {
        double diferencia = (uDestino.equals("segundo") ? 60 :
                             uDestino.equals("hora") ? 0.0166667 :
                             uDestino.equals("dia") ? 0.00069444583333 :
                             uDestino.equals("semana") ? 9.920654761857141876e-5 : 2.283107086994972884e-5);
        
        return monto * diferencia;
    }
    
    private double convertFromHour(double monto, String uDestino) {
        double diferencia = (uDestino.equals("segundo") ? 3600 :
                             uDestino.equals("minuto") ? 60 :
                             uDestino.equals("dia") ? 0.0416667 :
                             uDestino.equals("semana") ? 0.00595238 : 0.00136986);
        
        return monto * diferencia;
    }
    
    private double convertFromDay(double monto, String uDestino) {
        double diferencia = (uDestino.equals("segundo") ? 86400 :
                             uDestino.equals("minuto") ? 1440 :
                             uDestino.equals("hora") ? 24 :
                             uDestino.equals("semana") ? 0.142857 : 0.0328767);
        
        return monto * diferencia;
    }
    
    private double convertFromWeek(double monto, String uDestino) {
        double diferencia = (uDestino.equals("segundo") ? 604800 :
                             uDestino.equals("minuto") ? 10080 :
                             uDestino.equals("hora") ? 168 :
                             uDestino.equals("dia") ? 7 : 0.230137);
        
        return monto * diferencia;
    }
    
    private double convertFromMonth(double monto, String uDestino) {
        double diferencia = (uDestino.equals("segundo") ? 2.628e+6 :
                             uDestino.equals("minuto") ? 43800 :
                             uDestino.equals("hora") ? 730.001 :
                             uDestino.equals("dia") ? 30.4167 : 4.34524);
        
        return monto * diferencia;
    }
}
