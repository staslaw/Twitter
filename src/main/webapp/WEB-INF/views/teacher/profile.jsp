<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.09.18
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher profile</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerTeacher.jsp"/>

<div class="container">
    <h3>Profil</h3>

    <table class="table">
        <tr>
            <th>Imię i nazwisko: </th>
            <td>${teacher.firstName} ${teacher.lastName}</td>
        </tr>
        <tr>
            <th>Lekcje: </th>
            <td><c:forEach items="${teacher.lessons}" var="lesson">
                <c:out value="${lesson.subject.name} ${lesson.team.name}"/><br>
            </c:forEach></td>
        </tr>
        <tr>
            <th>Przedmioty: </th>
            <td><c:forEach items="${teacher.subjects}" var="subject">
                <c:out value="${subject.name}"/><br>
            </c:forEach></td>
        </tr>
        <tr>
            <th>Wychowawstwo: </th>
            <td>${teacher.educatorGroup}</td>
        </tr>
        <tr>
            <th>Email: </th>
            <td>${teacher.username}</td>
        </tr>
        <tr>
            <th>Opis: </th>
            <td>${teacher.description}</td>
        </tr>
    </table>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
