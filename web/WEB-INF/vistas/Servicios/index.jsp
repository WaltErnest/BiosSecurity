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
        <form>
            <table class="table">
                <tr>
                    <td>
                        <p>Datos del cliente:</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="servicios?accion=buscarCliente"><img src="images/searchicon.png" alt="Buscar Cliente" title="Buscar cliente..." >Buscar</a>&nbsp;&nbsp;
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${!empty cliente}">
                        <jsp:useBean id="cliente" type="compartidos.beans.entidades.Cliente" scope="session" />
                        <tr><td><strong>Cédula:</strong><jsp:getProperty name="cliente" property="cedula"/>  </td></tr>
                        <tr><td><strong>Nombre:</strong><jsp:getProperty name="cliente" property="nombre"/>  </td></tr>
                        <tr><td><strong>Dirección de Cobro:</strong><jsp:getProperty name="cliente" property="direccionCobro"/>  </td></tr>
                        <tr><td><strong>Barrio:</strong><jsp:getProperty name="cliente" property="barrioDirCobro"/>  </td></tr>
                        <tr><td><strong>Teléfono:</strong><jsp:getProperty name="cliente" property="telefono"/>  </td></tr>
                    </c:when>
                    <c:otherwise>
                        <tr><td><strong>Cédula:</strong><input type="text" name="cedulaCliente"></td></tr>
                        <tr><td><strong>Nombre:</strong><input type="text" name="nombreCliente"</td></tr>
                        <tr><td><strong>Dirección de Cobro:</strong><input type="text" name="direccionCobroCliente"</td></tr>
                        <tr><td><strong>Barrio:</strong><input type="text" name="barrioCobroCliente"</td></tr>
                        <tr><td><strong>Teléfono:</strong><input type="text" name="telefonoCliente"</td></tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td>
                        <p>Datos de la Propiedad:</p>                       
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="servicios?accion=buscarPropiedad"><img src="images/searchicon.png" alt="Buscar Propiedad" title="Buscar propiedad..." >Buscar</a>&nbsp;&nbsp;
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${!empty propiedad}">
                        <jsp:useBean id="propiedad" type="compartidos.beans.entidades.Propiedad" scope="session"/>
                        <tr><td><strong>Número:</strong><jsp:getProperty name="numeroPropiedad" property="numeroPropiedad"/>  </td></tr>
                        <tr><td><strong>Tipo:</strong><jsp:getProperty name="tipoPropiedad" property="tipoPropiedad"/>  </td></tr>
                        <tr><td><strong>Dirección:</strong><jsp:getProperty name="direccionPropiedad" property="direccion"/>  </td></tr>
                    </c:when>
                    <c:otherwise>
                        <tr><td><strong>Número:</strong><input type="text" name="numeroPropiedad"></td></tr>
                        <tr><td><strong>Tipo:</strong><input type="text" name="tipoPropiedad"</td></tr>
                        <tr><td><strong>Dirección:</strong><input type="text" name="direccionPropiedad"</td></tr>
                    </c:otherwise>
                </c:choose>
            </table>
            <input class="btn"type="submit" name="altaServicio" value="Dar de alta el servicio">
        </form>
        <t:mensaje/>
        <ul>
            <li><a href="index.jsp">Volver...</a></li>
        </ul>
    </jsp:body>
</t:paginaMaestra>
