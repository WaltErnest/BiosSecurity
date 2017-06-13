<%-- 
    Document   : main
    Created on : 05/06/2017, 11:31:36 AM
    Author     : desquitin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    String nombre;
%>
<%
    nombre = session.getAttribute("name").toString();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/log.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <title>Main</title>
    </head>
    <body>
        <ul>
            <li><a class="active" href="#home">Inicio</a></li>
            <li><a href="#servicio">Servicios</a></li>
            <li><a href="#empleado">Empleados</a></li>
            <li><a href="#dispositivo">Dispositivos</a></li>
            <li style="float:right"><a href="#cerrar">Cerrar</a></li>
        </ul>
        <h1 style="color: aliceblue;">bienvenio/a: <%=nombre%></h1>
    </body>
</html>
