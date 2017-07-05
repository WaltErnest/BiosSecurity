<%-- 
    Document   : index
    Created on : 26/06/2017, 12:38:06 PM
    Author     : desquitin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Menú Principal">
    <jsp:body>
        <jsp:useBean id="precios" class="compartidos.beans.entidades.Precios" scope="session">
            <%--<jsp:setProperty name="precios" property="pbA" value="1" />--%>
        </jsp:useBean>       

         <%--<jsp:getProperty name="precios" property="pbA" />--%>

        <h2 class="tableHeader">Precios servicios</h2>
        <form accion="ControladorPrecios" method="post" accept-charset="ISO-8859-1">
            <table style="width: 440px;">
                <tr>
                    <td>Precio base alarmas: </td><td><input type="text" name="pbA" /></td>
                </tr>
                <tr>
                    <td>Precio base camaras: </td><td><input type="text" name="pbC" /></td>
                </tr>
                <tr>
                    <td>Adicional por alarma: </td><td><input type="text" name="apA" /></td>
                </tr>
                <tr>
                    <td>Adicional por camara: </td><td><input type="text" name="apC" /></td>
                <tr>
                    <td>Tasa monitoreo alarmas: </td><td><input type="text" name="tmA" /></td>
                </tr>
                <tr>
                    <td>Tasa monitoreo camaras: </td><td><input type="text" name="tmC" /></td>
                </tr>

                <tr align="center">                
                    <td colspan="2"  ><input class="btn" type="submit" name="Actualizar" value="Actualizar" /></td>
                </tr>
            </table>
            <t:mensaje />
        </form>
    </jsp:body>
</t:paginaMaestra>
