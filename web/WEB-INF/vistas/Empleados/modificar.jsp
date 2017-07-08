<%-- 
    Document   : modificar
    Created on : Jun 26, 2017, 11:55:06 AM
    Author     : Ernesto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Modificar Empleado">
    <jsp:body>
        <c:if test="${!ocultarFormulario}">
            <t:editorEmpleados deshabilitarClave="true" idFoco="nombre" textoBoton="Modificar" />
        </c:if>
        
        <p><a href="empleados">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
