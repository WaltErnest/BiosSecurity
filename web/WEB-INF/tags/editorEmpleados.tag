<%-- 
    Document   : editorEmpleados
    Created on : 07-jul-2017, 11:44:43
    Author     : Ernesto
--%>

<%@tag description="Editor empleados" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="idFoco" required="true"%>
<%@attribute name="textoBoton" required="true"%>

<%-- any content can be specified here e.g.: --%>
<form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <td>Cédula:</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" name="cedula" value="${!empty empleado ? empleado.cedula : param.cedula}" readonly="readonly" id="cedula" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="cedula" value="${!empty empleado ? empleado.cedula : param.cedula}" id="cedula" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td>
                <input type="text" name="nombre" value="${!empty empleado ? empleado.nombre : param.nombre}" id="nombre" />
            </td>
        </tr>

        <c:set var="sueldo" scope="page" value="${!empty empleado ? empleado.sueldo : param.sueldo}" />

        <c:catch var="ex">
            <fmt:formatNumber type="number" pattern="0.00" value="${!empty empleado ? empleado.sueldo : param.sueldo}" var="sueldo" scope="page" />
        </c:catch>

        <tr>
            <td>Sueldo:</td>
            <td>
                <input type="text" name="sueldo" value="${sueldo}" />
            </td>
        </tr>

        <jsp:doBody/>

        <tr>
            <td></td>
            <td>
                <input type="submit" name="accion" value="${textoBoton}" />
            </td>
        </tr>
    </table>
</form>

<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>