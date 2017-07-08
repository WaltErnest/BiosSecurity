<%-- 
    Document   : index
    Created on : Jun 24, 2017, 4:45:45 PM
    Author     : Mathias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:paginaMaestra titulo="Registrar Servicio">
    <jsp:body>
        <fmt:setLocale value="en-US" />
        <h2 class="tableHeader">Registrar Servicio</h2>
            <table class="table">
                <h3>Datos del cliente:</h3>
                    <tr>
                        <td>
                            <form>
                                <p><input class="input" type="text" name="buscarCliente" value="${param.buscarCliente}" id="cedulaCliente" placeholder="Escriba la cédula..." /><input class="btn" type="submit" value="Buscar" /></p>
                            </form>
                        </td>
                    </tr>
                <tr>
                    <th>Cédula</th><th>Nombre</th><th>Dirección</th><th>Barrio</th><th>Teléfono</th><th></th>
                </tr>
                    <tr>
                        <c:if test="${!empty cliente}">
                            <jsp:useBean id="cliente" type="compartidos.beans.entidades.Cliente" scope="request" />
                            <td><jsp:getProperty name="cliente" property="cedula"/>  </td>
                            <td><jsp:getProperty name="cliente" property="nombre"/>  </td>
                            <td><jsp:getProperty name="cliente" property="direccionCobro"/>  </td>
                            <td><jsp:getProperty name="cliente" property="barrioDirCobro"/>  </td>
                            <td><jsp:getProperty name="cliente" property="telefono"/>  </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>
                            <a href="servicios?accion=altaCliente">Agregar</a>
                            <a href="servicios?accion=modificarCliente&cedula=${cliente.cedula}">Modificar</a>
                        </td>
                    </tr>
            </table>
        <t:mensaje/>
        <ul>
            <li><a href="index.jsp">Volver...</a></li>
        </ul>
    </jsp:body>
</t:paginaMaestra>
