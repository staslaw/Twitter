<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 10.09.18
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin group details</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Szczegóły klasy ${team.name}</h3>
    <table class="table">
        <tr>
            <td>Oznaczenie: </td>
            <td><c:out value="${team.name}"/></td>
        </tr>
        <tr>
            <td>Wychowawca: </td>
            <td><c:out value="${team.educator}"/></td>
        </tr>
        <tr>
            <td>Opis: </td>
            <td><c:out value="${team.description}"/></td>
        </tr>
        <tr>
            <td>Uczniowie: </td>
            <td><c:forEach items="${team.students}" var="student">
                <c:out value="${student.firstName} ${student.lastName}"/><br>
            </c:forEach></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="subjects" class="btn-default">Lekcje</a></td>
        </tr>
    </table>

    <a href="update" class="btn btn-default">Edytuj</a>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
