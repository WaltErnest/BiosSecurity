<%-- 
    Document   : agregar
    Created on : 06/07/2017, 07:59:19 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Agregar Dispositivo">
    <jsp:body>
        <t:editorDispositivos />
        
        <p><a href="inicio">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>