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
            <td><strong>Cédula:</strong></td>
            <td>
                <c:choose>
                    <c:when test="${deshabilitarClave}">
                        <input type="text" name="cedula" value="${!empty empleado ? empleado.cedula : param.cedula}" readonly="readonly" id="cedula" />
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="cedula" required oninvalid="this.setCustomValidity('Cédula inválida o vacía')" oninput="setCustomValidity('')" pattern="[0-9]{7}" maxlength="7" value="${!empty empleado ? empleado.cedula : param.cedula}" id="cedula" />
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        
        <tr>
            <td><strong>Clave:</strong></td>
            <td>
                <input type="password" name="clave" required oninvalid="this.setCustomValidity('La clave debe tener de 8 a 10 carácteres.')" oninput="setCustomValidity('')" pattern=".{8,10}" maxlength="10" value="${!empty empleado ? empleado.clave : param.clave}" />
            </td>
        </tr>
        
        <tr>
            <td><strong>Nombre:</strong></td>
            <td>
                <input type="text" name="nombre" required pattern="[A-Za-z\s]{1,50}" oninvalid="this.setCustomValidity('Nombre inválido o vacío.')" oninput="setCustomValidity('')" maxlength="50" value="${!empty empleado ? empleado.nombre : param.nombre}" id="nombre" />
            </td>
        </tr>
        
        <tr>
            <td><strong>Fecha de ingreso:</strong></td>
            <td>
                <input type="date" required oninvalid="this.setCustomValidity('Seleccione una fecha.')" oninput="setCustomValidity('')" name="fechaIngreso" value="${!empty empleado ? empleado.fechaIngreso : param.fechaIngreso}" min="2000-01-02" />
            </td>
        </tr>

        <c:set var="sueldo" scope="page" value="${!empty empleado ? empleado.sueldo : param.sueldo}" />

        <c:catch var="ex">
            <fmt:formatNumber type="number" pattern="0.00" value="${!empty empleado ? empleado.sueldo : param.sueldo}" var="sueldo" scope="page" />
        </c:catch>

        <tr>
            <td><strong>Sueldo:</strong></td>
            <td>
                <input type="text" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01" oninvalid="this.setCustomValidity('Sueldo inválido o vacío.')" oninput="setCustomValidity('')" name="sueldo" value="${sueldo}" />
            </td>
        </tr>

        <c:if test="${param.tipo eq 'Tecnico'}">
            <tr>
                <td><strong>Experto en alarmas:</strong> </td><td>
                    <input type="checkbox" name="alarmas"
                           <c:choose>
                               <c:when test="${!empty empleado}">
                                   <c:if test="${empleado.alarmas}">checked</c:if>
                               </c:when>
                           </c:choose> 
                           >
                </td>
            </tr>
            <tr>
                <td><strong>Experto en cámaras:</strong> </td><td>
                    <input type="checkbox" name="camaras"
                           <c:choose>
                               <c:when test="${!empty empleado}">
                                   <c:if test="${empleado.camaras}">checked</c:if>
                               </c:when>
                           </c:choose> 
                           >
                </td>
            </tr>
        </c:if>

        <c:if test="${param.tipo eq 'Cobrador'}">
            <tr>
                <td><strong>Tipo de transporte:</strong> </td><td>
                    <input type="text" name="tipoTransporte" required maxlength="30" value="${!empty empleado ? empleado.tipoTransporte : param.tipoTransporte}" id="tipoTransporte" />
                </td>
            </tr>
        </c:if>

        <jsp:doBody/>

        <tr>
            <td></td>
            <td>
                <input class="btn" type="submit" name="accion" value="${textoBoton}" />
            </td>
        </tr>
    </table>
</form>

<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>