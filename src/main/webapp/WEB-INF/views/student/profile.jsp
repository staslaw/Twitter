<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.09.18
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student profile</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headStudent.jsp"/>
<div class="container">
    <h3>Profil</h3>

    <table class="table">
        <tr>
            <th>Imię i nazwisko: </th>
            <td>${student.firstName} ${student.lastName}</td>
        </tr>
        <tr>
            <th>Lekcje: </th>
            <td><c:forEach items="${student.team.lessons}" var="lesson">
                <c:out value="${lesson.subject.name}: ${lesson.teacher.firstName} ${lesson.teacher.lastName}"/><br>
            </c:forEach></td>
        </tr>
        <tr>
            <th>Klasa: </th>
            <td>${student.team.name}</td>
        </tr>
        <tr>
            <th>Email: </th>
            <td>${student.username}</td>
        </tr>
        <tr>
            <th>Opis: </th>
            <td>${student.description}</td>
        </tr>
    </table>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>

</body>
</html>
