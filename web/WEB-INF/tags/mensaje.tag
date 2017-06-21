<%-- 
    Document   : mensaje
    Created on : 21/06/2017, 01:14:14 PM
    Author     : desquitin
--%>

<%@tag description="Párrafo de mensaje." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${!empty mensaje}">
    <center><p style="color:red" class="<c:if test="${fn:contains(mensaje, '¡ERROR!')}">error</c:if>">${mensaje}</p></center> 
</c:if>