<%-- 
    Document   : editorCliente
    Created on : Jul 2, 2017, 7:38:48 PM
    Author     : Mathias
--%>

<%@tag description="Editor que contiene todos los campos relativos a un cliente" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="textoBoton" required="true"%>

<%-- any content can be specified here e.g.: --%>
<fmt:setLocale value="en-US" />


<form method="post" accept-charset="ISO-8859-1">
    <table>
        <tr>
            <th colspan="2"><h3 class="tableHeader">Datos del Cliente:</h3></th>
        </tr>
        <tr>
            <td>Cédula:</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" name="cedula" value="${!empty cliente ? cliente.cedula : param.cedula}" readonly="readonly" id="cedula" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="cedula" value="${!empty cliente ? cliente.cedula : param.cedula}" id="cedula" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" value="${!empty cliente ? cliente.nombre : param.nombre}" id="nombre" /></td>
        </tr>
        <tr>
            <td>Dirección:</td>
            <td><input type="text" name="direccionCobro" value="${!empty cliente ? cliente.direccionCobro : param.direccionCobro}" id="direccionCobro"</td>
        </tr>
            <td>Barrio:</td>
            <td><input type="text" name="barrioDirCobro" value="${!empty cliente ? cliente.barrioDirCobro : param.barrioDirCobro}" id="barrioDirCobro"</td>
        </tr>
        <tr>
            <td>Teléfono:</td>
            <td><input type="text" name="telefono" value="${!empty cliente ? cliente.telefono : param.telefono}" id="telefono"</td>
        </tr>
        <tr>
            <td><input class="btn" type="submit" name="accion" value="${textoBoton}"</td>
        </tr>
    </table>
</form>

<h2>${message}</h2>