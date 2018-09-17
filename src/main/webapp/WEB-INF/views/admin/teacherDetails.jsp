<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 11.09.18
  Time: 00:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin teacher details</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Szczegóły nauczyciela ${teacher.firstName} ${teacher.lastName}</h3>
    <table class="table">
        <tr>
            <td>Imię: </td>
            <td><c:out value="${teacher.firstName}"/></td>
        </tr>
        <tr>
            <td>Nazwisko: </td>
            <td><c:out value="${teacher.lastName}"/></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><c:out value="${teacher.username}"/></td>
        </tr>
        <tr>
            <td>Przedmioty: </td>
            <td><c:forEach items="${teacher.subjects}" var="subject">
                <c:out value="${subject.name}"/></c:forEach></td>
        </tr>
        <tr>
            <td>Wychowawca klasy: </td>
            <td><c:out value="${teacher.educatorGroup}"/></td>
        </tr>
        <tr>
            <td>Opis: </td>
            <td><c:out value="${teacher.description}"/></td>
        </tr>
    </table>

    <a href="update?id=${teacher.id}" class="btn btn-default">Edytuj</a>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
