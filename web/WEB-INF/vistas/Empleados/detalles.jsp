<%-- 
    Document   : detalles
    Created on : Jun 26, 2017, 11:56:00 AM
    Author     : Ernesto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Ver Empleado">
    <jsp:body>
        <c:if test="${!empty empleado}">
            <jsp:useBean id="empleado" type="compartidos.beans.entidades.Empleado" scope="request" />

            <h2 class="tableHeader"><jsp:getProperty name="empleado" property="nombre" /></h2>

            <table style="width: 440px;">
                <tr><td><strong>Tipo de empleado:</strong></td><td> ${empleado.class.simpleName}</td></tr>

                <tr><td><strong>Cédula:</strong></td><td> <jsp:getProperty name="empleado" property="cedula" /></td></tr>

                <tr><td><strong>Nombre:</strong> </td><td><jsp:getProperty name="empleado" property="nombre" /></td></tr>

                <tr><td><strong>Fecha de ingreso:</strong> </td><td><jsp:getProperty name="empleado" property="fechaIngreso" /></td></tr>

                <tr><td><strong>Sueldo:</strong> </td><td><jsp:getProperty name="empleado" property="sueldo" /></td></tr>


                <c:if test="${param.tipo eq 'Tecnico'}">
                    <tr>
                        <td><strong>Experto en alarmas:</strong> </td><td>
                            <c:choose>
                                <c:when test="${empleado.alarmas}">Sí</c:when>
                                <c:otherwise>No</c:otherwise>
                            </c:choose> 
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Experto en cámaras:</strong> </td><td>
                            <c:choose>
                                <c:when test="${empleado.camaras}">Sí</c:when>
                                <c:otherwise>No</c:otherwise>
                            </c:choose> 
                        </td>
                    </tr>
                </c:if>

                <c:if test="${param.tipo eq 'Cobrador'}">
                    <tr>
                        <td><strong>Tipo de transporte:</strong> </td><td>
                            ${empleado.tipoTransporte}
                        </td>
                    </tr>
                </c:if>
            </table>
        </c:if>

        <p><a href="empleados">Volver...</a></p>

        <t:mensaje />
    </jsp:body>
</t:paginaMaestra>
