<%-- 
    Document   : eliminar
    Created on : 09/07/2017, 02:46:39 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Eliminar Empleado">
    <jsp:body>
        <c:if test="${!empty dispositivo}">
            <p>¿Está segur@ que desea eliminar el dispositivo con Nº <strong>${dispositivo.numeroInventario}</strong> ?</p>
            
            <form method="post" accept-charset="ISO-8859-1">
                <input type="hidden" name="inventario" value="${dispositivo.numeroInventario}" />
                <input class="btn" type="submit" name="accion" value="Eliminar" />
            </form>
        </c:if>
        
        <p><a href="dispositivos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
