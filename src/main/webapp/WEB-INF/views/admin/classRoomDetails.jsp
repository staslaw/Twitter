<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 10.09.18
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin class room details</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>
<div class="container">
    <h3>Szczegóły sali nr ${classRoom.number}</h3>
    <table class="table">
        <tr>
            <td>Numer: </td>
            <td><c:out value="${classRoom.number}"/></td>
        </tr>
        <tr>
            <td>Przedmiot: </td>
            <td><c:out value="${classRoom.subject}"/></td>
        </tr>
        <tr>
            <td>Opis: </td>
            <td><c:out value="${classRoom.description}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="update?id=${classRoom.id}" class="btn btn-default">Edytuj</a></td>
        </tr>
    </table>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
