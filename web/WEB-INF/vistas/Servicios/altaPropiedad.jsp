<%-- 
    Document   : altaPropiedad
    Created on : Jul 8, 2017, 9:17:54 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Alta Cliente">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <t:editorPropiedad textoBoton="Agregar">
        </t:editorPropiedad>
        <t:mensaje/>
    </jsp:body>
</t:paginaMaestra>