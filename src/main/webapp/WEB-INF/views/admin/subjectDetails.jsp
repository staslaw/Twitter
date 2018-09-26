<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 10.09.18
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin subject details</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Szczegóły przedmiotu ${subject.name}</h3>
    <table class="table">
        <tr>
            <td>Nazwa: </td>
            <td><c:out value="${subject.name}"/></td>
        </tr>
        <tr>
            <td>Opis: </td>
            <td><c:out value="${subject.description}"/></td>
        </tr>
        <tr>
            <td>Klasy uczące się tego przedmiotu: </td>
            <td><c:forEach items="${teams}" var="team">
            <c:out value="${team.name}"/><br></c:forEach></td>
        </tr>
        <tr>
            <td>Sale do tego przedmiotu: </td>
            <td><c:forEach items="${subject.classRooms}" var="classRoom">
                <c:out value="${classRoom.number}"/><br></c:forEach></td>
        </tr>
        <tr>
            <td>Nauczyciele tego przedmiotu: </td>
            <td><c:forEach items="${subject.teachers}" var="teacher">
                <c:out value="${teacher.firstName} ${teacher.lastName}"/><br></c:forEach></td>
        </tr>
    </table>

    <a href="update?id=${subject.id}" class="btn btn-default">Edytuj</a>
</div>


<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
