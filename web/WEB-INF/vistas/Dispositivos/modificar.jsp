<%-- 
    Document   : modificar
    Created on : 09/07/2017, 02:47:09 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Modificar Empleado">
    <jsp:body>
        <c:if test="${!ocultarFormulario}">
            <t:editorDispositivos deshabilitarClave="true" idFoco="inventario" textoBoton="Modificar" />
        </c:if>
        
        <p><a href="dispositivos">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>