<%-- 
    Document   : otra
    Created on : 19/06/2017, 12:34:54 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! String msj, name, pass;%>
<%
    msj = session.getAttribute("msj").toString();
    name = session.getAttribute("name").toString();
    pass = session.getAttribute("pass").toString();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! Nº devuelto por persistencia <%=msj%> nombre <%=name%> pass <%=pass%></h1>
    </body>
</html>
