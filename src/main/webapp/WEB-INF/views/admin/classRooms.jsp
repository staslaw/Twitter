<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin class rooms</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
<h3>Lista sal</h3>
<table class="table table-striped">
<c:if test="${classRooms.size() != 0}">
    <tr>
        <th>Numer</th>
        <th>Przedmiot</th>
        <th>Szczegóły</th>
        <th>Usuń</th>
    </tr>
</c:if>
    <c:if test="${classRooms.size() == 0}">
        <c:out value="brak sal do wyświetlenia"/>
    </c:if>
<c:forEach items="${classRooms}" var="classRoom">
    <tr>
        <td><c:out value="${classRoom.number}"/></td>
        <td><c:out value="${classRoom.subject}"/></td>
        <td><a href="classRooms/details?id=${classRoom.id}">szczegóły</a></td>
        <td><a class="confirm btn btn-success" href="classRooms/remove?id=${classRoom.id}">usuń</a></td>
    </tr>
</c:forEach>
</table>

<br>
<a href="classRooms/add" class="btn btn-default">Dodaj salę</a>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
