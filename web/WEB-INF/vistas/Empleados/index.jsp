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
            <input class="buscar" id="buscar" value="${param.search}" name="search" placeholder="Buscar..."/>
        </form>

        <p></p>
        <c:if test="${sessionScope.usuario.class.simpleName eq 'Administrativo'}">
            <form action="empleados">
                <label>Agregar: </label>

                <select name="tipo">
                    <option value="A" selected="selected">Administrador</option>
                    <option value="T">Tecnico</option>
                    <option value="C">Cobrador</option>
                </select>

                <input type="submit" class="otra" name="accion" value="agregar" />
            </form>
            <p></p>
        </c:if>

        <table>
            <tr>
                <th>Cédula</th><th>Nombre</th><th>Fecha de ingreso</th><th>Sueldo</th><th></th>
            </tr>

            <c:forEach items="${empleados}" var="empleado">
                <tr>
                    <td>${empleado.cedula}</td>
                    <td>${empleado.nombre}</td>
                    <td>${empleado.fechaIngreso}</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="0.00" value="${empleado.sueldo}" />
                    </td>
                    <td>
                        <a href="empleados?accion=detalles&cedula=${empleado.cedula}&tipo=${empleado.class.simpleName}"><img src="images/view.png" alt="Ver" title="Ver..." ></a>&nbsp;&nbsp;
                            <c:if test="${sessionScope.usuario.class.simpleName eq 'Administrativo'}">
                            <a href="empleados?accion=modificar&cedula=${empleado.cedula}&tipo=${empleado.class.simpleName}"><img src="images/edit.png" alt="Modificar" title="Modificar..." ></a>&nbsp;&nbsp;
                            <a href="empleados?accion=eliminar&cedula=${empleado.cedula}&tipo=${empleado.class.simpleName}"><img src="images/delete.png" alt="Eliminar" title="Eliminar..." ></a>
                            </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <t:mensaje />

        <script>
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:paginaMaestra>