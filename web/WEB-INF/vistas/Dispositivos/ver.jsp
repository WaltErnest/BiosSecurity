<%-- 
    Document   : ver
    Created on : 09/07/2017, 01:16:27 PM
    Author     : Diego
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Ver dispositivo">
    <jsp:body>
        <c:if test="${!empty dispositivo}">
            <jsp:useBean id="dispositivo" type="compartidos.beans.entidades.Dispositivo" scope="request" />
            <table style="width: 440px;">
                <tr>
                    <td> Nº inventario: </td><td>${dispositivo.numeroInventario}</td>               
                </tr>
                <tr>
                    <td>Descripción: </td><td>${dispositivo.descripcionUbicacion}</td>               
                </tr>
            </table>
        </c:if>
        
        <p><a href="dispositivos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>