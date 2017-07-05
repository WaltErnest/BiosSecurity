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
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <link rel="stylesheet" type="text/css" href="css/formularios.css">

        <title>${titulo}</title>

        <link rel="stylesheet" href="css/general.css" />
    </head>
    <body>
        <ul>
            <li><a class="tablinks" onclick="sel(event, 'inicio')" id="inicio" href="#Inicio">Inicio</a></li>
            <li><a class="tablinks" onclick="sel(event, 'servicio')" id="servicio" href="#servicio">Servicios</a></li>
            <li><a class="tablinks" onclick="sel(event, 'empleado')" id="empleado" href="#empleado">Empleados</a></li>
            <li><a class="tablinks" onclick="sel(event, 'dispositivo')" id="dispositivo" href="#dispositivo">Dispositivos</a></li>
            <li><a class="tablinks" onclick="sel(event, 'cobros')" id="cobros" href="#Cobros">Cobros</a></li>          
            <li><a class="tablinks" onclick="sel(event, 'index')" id="index" href="precios">Precios</a></li>                
            <li style="float:right"><a href="login">Cerrar</a></li>
        </ul>
        <jsp:doBody />
        <script>
            function sel(evt, Name) {
                var i, tablinks;
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(Name).style.display = "block";
                evt.currentTarget.className += " active";
            }
            document.getElementById("defaultOpen").click();
        </script>
    </body>
</html>