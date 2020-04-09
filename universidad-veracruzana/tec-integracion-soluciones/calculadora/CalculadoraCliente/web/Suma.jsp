<%-- 
    Document   : Suma
    Created on : 06/03/2018, 08:45:45
    Author     : caioaba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="CalcServlet" method="post">
            <input type="text" name="param1" />
            <input type="text" name="param2" />
            <select name="operacion">
                <option value="suma">Suma</option>
                <option value="resta">Resta</option>
                <option value="multiplicacion">Multiplicación</option>
                <option value="division">División</option>
                <option value="exponenciacion">Exponenciación</option>
                <option value="raizc">Raiz Cuadrada</option>                
            </select>
            <input type="submit" value="send" />
        </form>
    </body>
</html>
