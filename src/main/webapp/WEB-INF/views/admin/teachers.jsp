<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Teachers</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Lista nauczycieli</h3>
    <table class="table table-striped">
        <c:if test="${teachers.size() != 0}">
            <thead>
                <tr>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Wychowawca klasy</th>
                    <th>Szczegóły</th>
                    <th>Usuń</th>
                </tr>
            </thead>
        </c:if>
        <c:if test="${teachers.size() == 0}">
            <c:out value="brak nauczycieli do wyświetlenia"/>
        </c:if>
        <c:forEach items="${teachers}" var="teacher">
            <tr>
                <td>${teacher.firstName}</td>
                <td>${teacher.lastName}</td>
                <td>${teacher.educatorGroup}</td>
                <td><a href="teachers/details?id=${teacher.id}">szczegóły</a></td>
                <td><a class="confirm btn btn-success" href="teachers/remove?id=${teacher.id}">usuń</a></td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="teachers/add" class="btn btn-default">Dodaj nauczyciela</a><br>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
