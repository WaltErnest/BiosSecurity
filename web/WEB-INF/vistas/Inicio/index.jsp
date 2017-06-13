<%-- 
    Document   : index
    Created on : 05/06/2017, 10:20:24 AM
    Author     : desquitin
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    private String name, pass, namedb, passdb, login;
%>

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
            <form class="login-container">
                <p><input type="text" name="name" placeholder="usuario"></p>
                <p><input type="password" name="pass" placeholder="contraseña"></p>
                <p><input type="submit" name="login" value="Login"></p>
                    <%
                        /*name = request.getParameter("name");
                        pass = request.getParameter("pass");*/
                        login = request.getParameter("login");
                        if ((login != null) && login.equals("Login")) {

                            Connection con = null;
                            PreparedStatement ps = null;
                            ResultSet rs = null;

                            String driverName = "com.mysql.jdbc.Driver";
                            String url = "jdbc:mysql://localhost:3306/biossecurity";
                            String user = "root";
                            String dbpsw = "root";
                            String sql = "select * from empleados where nombre = ? and clave = ?";

                            String name = request.getParameter("name");
                            String pass = request.getParameter("pass");
                            if ((!(name.equals(null) || name.equals("")) && !((pass.equals(null) || pass.equals(""))))) {
                                try {
                                    Class.forName(driverName);
                                    con = DriverManager.getConnection(url, user, dbpsw);
                                    ps = con.prepareStatement(sql);
                                    ps.setString(1, name);
                                    ps.setString(2, pass);

                                    rs = ps.executeQuery();
                                    if (rs.next()) {
                                        namedb = rs.getString("nombre");
                                        passdb = rs.getString("clave");

                                        if (name.equals(namedb) && pass.equals(passdb)) {
                                            session.setAttribute("name", namedb);
                                            response.sendRedirect("main.jsp");
                                        }
                                    } else {
                                        //response.sendRedirect("error.jsp");
                                        %>
                                        <center><p style="color:red">Error: usuario y/o contraseña incorrectos</p></center>
                                        <%
                                    }
                                    rs.close();
                                    ps.close();
                                } catch (SQLException sqe) {
                                    out.println(sqe);
                                }
                            } else {
                                    %>
                                <center><p style="color:red">Error en Login</p></center>
                                <%                                
                                }
                        }%>
            </form>
        </div>
    </body>
</html>
