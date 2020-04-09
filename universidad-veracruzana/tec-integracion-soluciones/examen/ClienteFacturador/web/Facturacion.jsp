<%-- 
    Document   : Facturacion
    Created on : 26/04/2018, 08:04:59
    Author     : caioaba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculo de Impuesto</title>
        <script>
            (() => window.onload = () => {
                let form = document.createElement("form");
                form.setAttribute("id", "form");
                form.setAttribute("method", "POST");
                form.setAttribute("action", "Facturar");
                
                document.getElementById("formHere").appendChild(form);
                document.getElementById("form").appendChild(document.getElementById("formElements"));
                
            })();
        </script>
    </head>
    <body>
        <h1>Calculo de Impuesto</h1>
        <div id="formHere"></div>
        <div id="formElements"> 
            <table>
                <tr>
                    <td>Nombre del Comercio:</td>
                    <td><input type="text" name="nombre" /></td>
                </tr>
                <tr>
                    <td>RFC:</td>
                    <td><input type="text" name="rfc" /></td>
                </tr>
                <tr>
                    <td>Monto:</td>
                    <td><input type="text" name="monto" /> </td>
                </tr>
                <tr>
                    <td>Tipo de Impuesto:</td>
                    <td>
                        <select name="impuesto">
                            <option value="iva">IVA</option>
                            <option value="isr">ISR</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;"><input type="submit" value="Enviar" /></td>
                </tr>
            </table>
        </div>
    </body>
</html>
