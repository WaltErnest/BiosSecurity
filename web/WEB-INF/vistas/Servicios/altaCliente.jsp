<%-- 
    Document   : altaCliente
    Created on : Jul 8, 2017, 1:36:31 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Registrar Servicio">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <t:editorCliente textoBoton="Agregar">
            
        </t:editorCliente>
        <t:mensaje/>
    </jsp:body>
</t:paginaMaestra>
