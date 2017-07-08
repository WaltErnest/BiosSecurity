<%-- 
    Document   : modificarCliente
    Created on : Jul 8, 2017, 1:37:37 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Modificar Cliente">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <c:if test="${!ocultarFormulario}">
            <t:editorCliente deshabilitarCedula="true" textoBoton="Modificar" />
        </c:if>
        <t:mensaje/>
    </jsp:body>
</t:paginaMaestra>
