<%-- 
    Document   : editorPropiedad
    Created on : Jul 8, 2017, 8:41:50 PM
    Author     : Mathias
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="textoBoton" required="true"%>
<fmt:setLocale value="en-US" />


<form method="post" accept-charset="ISO-8859-1">
    <table class="table">
        <tr>
            <th colspan="2"><h3 class="tableHeader">Datos de la Propiedad:</h3></th>
        </tr>
        <tr>
            <td>Numero:</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input readonly="true" name="numeroPropiedad" value="${!empty propiedad ? propiedad.numeroPropiedad : param.numeroPropiedad}" id="numeroPropiedad" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="numeroPropiedad" value="${!empty propiedad ? propiedad.numeroPropiedad : param.numeroPropiedad}" id="numeroPropiedad"  />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Tipo:</td>
            <td>
                <input type="text" name="tipoPropiedad" value="${!empty propiedad ? propiedad.tipoPropiedad : param.propiedad.tipoPropiedad}" id="tipoPropiedad" />
            </td>
        </tr>
        <tr>
            <td>Dirección:</td>
            <td>
                <input type="text" name="direccionPropiedad" value="${!empty propiedad ? propiedad.direccion : param.direccionPropiedad}" id="direccionPropiedad" />
            </td>
        </tr>
        <tr>
            <td>Dueño:</td>
            <td>
                <input type="text" name="dueno" value="${!empty cliente ? cliente.cedula : param.cliente.cedula}" id="dueno" />
            </td>
        </tr>
        <tr>
            <td>
                <input class="btn" type="submit" name="accion" value="${textoBoton}" />
                <c:if test="${!empty propiedad}">
                    <a href="servicios?accion=modificarPropiedad&cedula=${cliente.cedula}&numeroPropiedad=${propiedad.numero}"><img src="images/edit.png" alt="Modificar Propiedad" title="Modificar propiedad..." ></a>&nbsp;&nbsp;
                    <a href="servicios?accion=agregarPropiedadServicio"><img src="images/agregar.png" alt="Agregar Propiedad a Servicio" title="Agregar Propiedad a servicio..."> </a>&nbsp;&nbsp;
                </c:if>
            </td>
        </tr>
    </table>
</form>
<h2>${message}</h2>