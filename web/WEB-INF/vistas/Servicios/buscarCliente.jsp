<%-- 
    Document   : buscarCliente
    Created on : Jul 9, 2017, 1:20:25 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Buscar Cliente">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <c:if test="${!ocultarFormulario}">
            <t:editorCliente deshabilitarClave="false" textoBoton="Buscar" />
        </c:if>
        <t:mensaje/>
    </jsp:body>
</t:paginaMaestra>
