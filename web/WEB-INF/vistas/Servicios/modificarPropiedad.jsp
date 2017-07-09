<%-- 
    Document   : modificarPropiedad
    Created on : Jul 8, 2017, 9:18:03 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Alta Cliente">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <t:editorPropiedad textoBoton="Modificar">
        <c:if test="${!ocultarFormulario}">
            <t:editorPropiedad deshabilitarClave="true" textoBoton="Modificar" />
        </c:if>
        </t:editorPropiedad>
        <t:mensaje/>
    </jsp:body>
</t:paginaMaestra>
