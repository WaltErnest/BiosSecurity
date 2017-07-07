<%-- 
    Document   : index
    Created on : Jun 26, 2017, 11:55:20 AM
    Author     : Ernesto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Empleados">
    <jsp:body>
        <p> </p>
        <style> 
            .buscar {
                width: 130px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
                background-color: white;
                background-image: url('images/searchicon.png');
                background-position: 10px 10px; 
                background-repeat: no-repeat;
                padding: 12px 20px 12px 40px;
                -webkit-transition: width 0.4s ease-in-out;
                transition: width 0.4s ease-in-out;
            }

            .buscar:focus {
                width: 100%;
            }
        </style>
        <fmt:setLocale value="en-US" />
        
        <form>
            <input class="buscar" name="search" placeholder="Buscar..."/>
        </form>
        
        <p><a class="otra" href="empleados?accion=agregar"><img src="images/agregar.png" alt="Agregar" title="Agregar..." ></a></p>
        
        <table>
            <tr>
                <th>Cédula</th><th>Nombre</th><th>Sueldo</th><th></th>
            </tr>
            
            <c:forEach items="${empleados}" var="empleado">
                <tr>
                    <td>${empleado.cedula}</td>
                    <td>${empleado.nombre}</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="0.00" value="${empleado.sueldo}" />
                    </td>
                    <td>
                        <a href="empleados?accion=ver&cedula=${empleado.cedula}"><img src="images/view.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="empleados?accion=modificar&cedula=${empleado.cedula}"><img src="images/edit.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="empleados?accion=eliminar&cedula=${empleado.cedula}"><img src="images/delete.png" alt="Eliminar" title="Eliminar..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>