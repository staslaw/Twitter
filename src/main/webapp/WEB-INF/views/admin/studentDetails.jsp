<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 10.09.18
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin student details</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Szczegóły ucznia ${student.firstName} ${student.lastName}</h3>
    <table class="table">
        <tr>
            <td>Imię: </td>
            <td><c:out value="${student.firstName}"/></td>
        </tr>
        <tr>
            <td>Nazwisko: </td>
            <td><c:out value="${student.lastName}"/></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><c:out value="${student.username}"/></td>
        </tr>
        <tr>
            <td>Klasa: </td>
            <td><c:out value="${student.team}"/></td>
        </tr>
        <tr>
            <td>Opis: </td>
            <td><c:out value="${student.description}"/></td>
        </tr>
    </table>

    <a href="update?id=${student.id}" class="btn btn-default">Edytuj</a>
</div>


<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
