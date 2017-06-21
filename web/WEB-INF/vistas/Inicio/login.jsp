<%-- 
    Document   : login
    Created on : 17/06/2017, 08:58:17 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/log.css">
    </head>
    <body>
        <!--h1>Hello World!</h1-->
        <div class="login">
            <!--div class="login-triangle"></div-->
            <h2 class="login-header">Bios Security</h2>            
            <form accion="ControladorInicio" method="post" class="login-container">
                <p><input type="text" name="name" placeholder="usuario"></p>
                <p><input type="password" name="pass" placeholder="contraseña"></p>
                <p><input type="submit" name="login" value="login"></p> 
            </form>
        </div>
    </body>
</html>
