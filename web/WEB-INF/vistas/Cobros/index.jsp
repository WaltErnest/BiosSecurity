<%-- 
    Document   : index
    Created on : 06/07/2017, 05:55:21 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:paginaMaestra titulo="Cobros" active="cobros">
    <jsp:body>
        <p></p>
        <h2 class="tableHeader">Cobros</h2>
        <form accion="ControladorCobros" method="post" accept-charset="ISO-8859-1">
            <table style="width: 440px;">
                <tr>
                    <td>Seleccione un mes:</td>
                    <td><div>
                            <label for="listaDesplegable"></label>
                            <select type="text"name="listaDesplegable" id="listaDesplegable">
                                <option value="0">Enero</option>
                                <option value="1">Febrero</option>
                                <option value="2">Marzo</option>
                                <option value="3">Abril</option>
                                <option value="4">Mayo</option>
                                <option value="5">Junio</option>
                                <option value="6">Julio</option>
                                <option value="7">Agosto</option>
                                <option value="8">Setiembre</option>
                                <option value="9">Octubre</option>
                                <option value="10">Noviembre</option>
                                <option value="11">Diciembre</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr align="center"> 
                    <td colspan="2"><input class="btn" type="submit" name="Generar" value="Generar" /></td>                    
                </tr>
            </table>
        </form>
    </jsp:body>
</t:paginaMaestra>