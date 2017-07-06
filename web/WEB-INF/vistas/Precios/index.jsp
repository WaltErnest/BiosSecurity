<%-- 
    Document   : index
    Created on : 26/06/2017, 12:38:06 PM
    Author     : desquitin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Precios" idactive="precios">
    <jsp:body>
        <jsp:useBean id="precio" class="compartidos.beans.entidades.Precios" scope="request"/>

        <h2 class="tableHeader">Precios servicios</h2>
        <form accion="ControladorPrecios" method="post" accept-charset="ISO-8859-1">
            <table style="width: 440px;">
                <tr>
                    <td>Precio base alarmas: </td><td><input pattern="[0-9]+([,\.][0-9]+)?" type="text" name="pbA" value="${!empty precio ? precio.pbA : param.pbA}"/></td>
                </tr>
                <tr>
                    <td>Precio base camaras: </td><td><input pattern="[0-9]+([,\.][0-9]+)?" type="text" name="pbC" value="${!empty precio ? precio.pbC : param.pbC}"/></td>
                </tr>
                <tr>
                    <td>Adicional por alarma: </td><td><input pattern="[0-9]+([,\.][0-9]+)?" type="text" name="apA" value="${!empty precio ? precio.apA : param.apA}"/></td>
                </tr>
                <tr>
                    <td>Adicional por camara: </td><td><input pattern="[0-9]+([,\.][0-9]+)?" type="text" name="apC" value="${!empty precio ? precio.apC : param.apC}"/></td>
                <tr>
                    <td>Tasa monitoreo alarmas: </td><td><input pattern="[0-9]+([,\.][0-9]+)?" type="text" name="tmA" value="${!empty precio ? precio.tmA : param.tmA}"/></td>
                </tr>
                <tr>
                    <td>Tasa monitoreo camaras: </td><td><input pattern="[0-9]+([,\.][0-9]+)?" type="text" name="tmC" value="${!empty precio ? precio.tmC : param.tmC}"/></td>
                </tr>

                <tr align="center">                
                    <td colspan="2"  ><input class="btn" type="submit" name="Actualizar" value="Actualizar" /></td>
                </tr>
            </table>
            <t:mensaje />
        </form>
    </jsp:body>
</t:paginaMaestra>
