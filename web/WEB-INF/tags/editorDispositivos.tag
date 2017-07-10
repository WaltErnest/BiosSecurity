<%-- 
    Document   : editorDispositivos
    Created on : 06/07/2017, 08:10:05 PM
    Author     : Diego
--%>

<%@tag description="Editor Dispositivos" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="idFoco" required="true"%>
<%@attribute name="textoBoton" required="true"%>
<link rel="stylesheet" type="text/css" href="css/formularios.css">
<%-- any content can be specified here e.g.: --%>
<fmt:setLocale value="en-US" />

<form method="post" accept-charset="ISO-8859-1">
    <table style="width: 440px;">
        <h2 class="tableHeader">Dispositivos</h2>
        <tr><td>Tipo:</td> 
            <c:choose>
                <c:when test="${!empty camara}">
                    <td> <input type="radio" name="disp" value="alarma" onclick="mostrarocultar(this)">Alarma 
                        <input type="radio" name="disp" value="camara" checked="checked">Camara 
                    </td>
                </c:when>
                <c:otherwise>
                    <td> <input type="radio" name="disp" value="alarma" checked="checked" onclick="mostrarocultar(this)">Alarma 
                        <input type="radio" name="disp" value="camara" onclick="mostrarocultar(this)">Camara 
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td>Nº Inventario</td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" pattern="[0-9]+([,\.][0-9]+)?" title="Sólo se aceptan numeros." name="inventario" value="${!empty dispositivo ? dispositivo.numeroInventario : param.numeroInventario}" readonly="readonly" id="inventario" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" pattern="[0-9]+([,\.][0-9]+)?" title="Sólo se aceptan numeros." name="inventario" value="${!empty dispositivo ? dispositivo.numeroInventario : param.numeroInventario}" id="inventario" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Descripción:</td>
            <td>
                <input type="text" name="descripcion" value="${!empty dispositivo ? dispositivo.descripcionUbicacion : param.descripcionUbicacion}" id="descripcion" />
            </td>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${!empty camara}">
                    <td id="lblinterior">Interior:</td>
                    <td>
                        <c:choose>
                            <c:when test="${!empty check}">
                                <input type="checkbox" name="interior" value="true" checked="checked" id="interior" />
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="interior" value="true"  id="interior" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:when>
            </c:choose>
        </tr>

        <tr>
            <td></td>
            <td>
                <input class="btn" type="submit" name="accion" value="${textoBoton}" />
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
    function mostrarocultar(rad)
    {
        if (rad.value == "alarma") {
            document.getElementById("interior").style.display = 'none';
            document.getElementById("lblinterior").style.display = 'none';
        } else {
            if (rad.value == "camara") {
                document.getElementById("interior").style.display = 'table-cell';
                document.getElementById("lblinterior").style.display = 'table-cell';
            }
        }
    }
</script>
