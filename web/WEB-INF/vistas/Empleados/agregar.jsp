<%-- 
    Document   : agregar
    Created on : Jun 26, 2017, 11:54:47 AM
    Author     : Ernesto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Agregar Empleado">
    <jsp:body>
        <t:editorEmpleados idFoco="cedula" textoBoton="Agregar">
            <jsp:body>
                
            </jsp:body>
        </t:editorEmpleados>
        
        <p><a href="inicio">Volver...</a></p>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>

