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
    <title>Admin Students</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Lista uczniów</h3>
    <table class="table table-striped">
    <c:if test="${students.size() != 0}">
        <thead>
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Klasa</th>
                <th>Szczegóły</th>
                <th>Usuń</th>
            </tr>
        </thead>
    </c:if>
        <c:if test="${students.size() == 0}">
            <c:out value="brak uczniów do wyświetlenia"/>
        </c:if>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.team}</td>
            <td><a href="students/details?id=${student.id}">szczegóły</a></td>
            <td><a class="confirm btn btn-success" href="students/remove?id=${student.id}">usuń</a></td>
        </tr>
    </c:forEach>
    </table>

    <br>
    <a href="students/add" class="btn btn-default">Dodaj ucznia</a><br>
</div>


<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
