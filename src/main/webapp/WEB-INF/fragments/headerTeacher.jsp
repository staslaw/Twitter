<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 11.09.18
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher home</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/teacher"/>">Nauczyciel</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/teacher/lessons"/>">Prowadzone lekcje</a></li>
            <li><a href="<c:url value="/teacher/profile"/>">Mój profil</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <form:form action="/logout" method="post">
                <button type="submit" value="Wyloguj" class="btn btn-link">
                    <span class="glyphicon glyphicon-log-in"></span> Wyloguj
            </form:form>
        </ul>
    </div>
</nav>

<c:if test="${not empty message}">
    <div class="alert alert-success">
            ${message}
    </div>
</c:if>
<c:if test="${not empty messageDanger}">
    <div class="alert alert-danger">
            ${messageDanger}
    </div>
</c:if>

</body>
</html>