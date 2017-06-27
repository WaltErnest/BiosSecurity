<%-- 
    Document   : index
    Created on : 26/06/2017, 12:38:06 PM
    Author     : desquitin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="precios" class="compartidos.beans.entidades.Precios" scope="session">
            <jsp:setProperty name="precios" property="pbA" value="1" />
        </jsp:useBean>       
        
        <p>Código: <jsp:getProperty name="precios" property="pbA" /></p>
               
        
        <form accion="ControladorPrecios" method="post" accept-charset="ISO-8859-1">
            <table>
                <tr>
                    <td>Precio base alarmas </td><td><input type="text" name="pbA" /></td>
                    <!--
                </tr>
                <tr>
                    <td>Descripción:</td><td><input type="text" name="descripcion" /></td>
                </tr>
                <tr>
                    <td>Precio:</td><td><input type="text" name="precio" /></td>
                </tr> -->
                <tr>
                    <td></td><td><input type="submit" name="cambiarProducto" value="Cambiar Producto" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
