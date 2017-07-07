<%-- 
    Document   : index
    Created on : 06/07/2017, 05:55:21 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:paginaMaestra titulo="Dispositivos" active="dispositivo">
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
        <form>
            <input class="buscar" name="search" placeholder="Buscar..">
        </form>
        <p><a class="otra" href="dispositivos?accion=agregar"><img src="images/agregar.png" alt="Agregar" title="Agregar..." ></a></p>
        <table>
            <tr>
                <th>Código</th><th>Descripción</th><<th></th>
            </tr>
            
            <c:forEach items="${dispositivos}" var="dispositivos">
                <tr>
                    <td class="texto-centro">${empleado.cedula}</td>
                    <td>${empleado.nombre}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${empleado.sueldo}" />
                    </td>
                    <td>
                        <a href="empleados?accion=ver&cedula=${empleado.cedula}"><img src="imagenes/glyphicons-52-eye-open.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                        <a href="empleados?accion=modificar&cedula=${empleado.cedula}"><img src="imagenes/glyphicons-31-pencil.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                        <a href="empleados?accion=eliminar&cedula=${empleado.cedula}"><img src="imagenes/glyphicons-192-minus-sign.png" alt="Eliminar" title="Eliminar..." ></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:paginaMaestra>
