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
    <title>Admin subjects</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Lista przedmiotów</h3>
    <table class="table table-striped">
        <c:if test="${subjects.size() != 0}">
            <thead>
                <tr>
                    <th>Nazwa</th>
                    <th>Szczegóły</th>
                    <th>Usuń</th>
                </tr>
            </thead>
        </c:if>
        <c:if test="${subjects.size() == 0}">
            <c:out value="brak przedmiotów do wyświetlenia"/>
        </c:if>
        <c:forEach items="${subjects}" var="subject">
            <tr>
                <td>${subject.name}</td>
                <td><a href="subjects/details?id=${subject.id}">szczegóły</a></td>
                <td><a class="confirm btn btn-success" href="subjects/remove?id=${subject.id}">usuń</a></td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="subjects/add" class="btn btn-default">Dodaj przedmiot</a><br>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
