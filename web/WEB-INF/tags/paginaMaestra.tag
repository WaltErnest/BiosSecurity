<%-- 
    Document   : masterPage
    Created on : 17/06/2017, 11:35:42 AM
    Author     : Diego
--%>

<%@tag description="Página maestra del sitio." pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%@attribute name="titulo" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!--link rel="stylesheet" type="text/css" href="css/log.css"-->
         <link rel="stylesheet" type="text/css" href="css/menu.css">

        <title>${titulo}</title>
        
        <link rel="stylesheet" href="css/general.css" />
    </head>
    <body>
            <ul>
            <li><a class="active" href="#home">Inicio</a></li>
            <li><a href="#servicio">Servicios</a></li>
            <li><a href="#empleado">Empleados</a></li>
            <li><a href="#dispositivo">Dispositivos</a></li>
            <li style="float:right"><a href="#cerrar">Cerrar</a></li>
        </ul>
     
        <!--jsp:doBody /-->
        
        <!--script src="js/general.js"></script-->
    </body>
</html>