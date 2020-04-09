/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soluciones.integracion.tec;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author caioaba
 */
@WebService(serviceName = "Facturacion")
public class Facturacion {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hacerFacturacion")
    public String hacerFacturacion(@WebParam(name = "rfc") String rfc, @WebParam(name = "monto") double monto, @WebParam(name = "impuesto") String tipoImpuesto, @WebParam(name = "nombre") String nombreComercio) {
        double iva = 0.15;
        double isr = 0.064;
        double impuesto = 0;
        Random random = new Random();
        
        if (tipoImpuesto.equals("iva")) impuesto = monto * iva;
        else impuesto = monto * isr;
        
        String timbre = UUID.randomUUID().toString().replace("-", "").substring(0, 30);
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        String hora = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        int folio = 100000 + random.nextInt(900000);        
        
        StringBuilder response = new StringBuilder("Factura: <br /><br />");
        response.append("Timbrado: " + timbre + "<br />");
        response.append("Fecha: " + fecha + "<br />");
        response.append("Hora: " + hora + "<br />");
        response.append("Folio: " + folio + "<br />");
        response.append("Nombre: " + nombreComercio + "<br />");
        response.append("Impuesto: MX$" + impuesto + "<br />");
        response.append("<i>(Monto Declarado: MX$" + monto + " / Impuestos aplicados: " + (tipoImpuesto.equals("iva") ? iva : isr) + "%)</i><br />");
        
        
        return response.toString();
    }
}
