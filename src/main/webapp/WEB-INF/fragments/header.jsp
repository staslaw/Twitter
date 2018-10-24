<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.10.18
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script type="text/javascript" src="webjars/jquery/3.1.0/jquery.min.js"></script>--%>
</head>
<body>

<div class="container-fluid" style="background-color:darkgreen;color:#fff">
    <div class="text-center">
        <h1>Twitter</h1>
    </div>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <c:if test="${user.id > 0}">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/user/main"/>">Home</a></li>
                <li><a href="<c:url value="/user/profile?id=${user.id}"/>">Profile</a></li>
                <li><a href="<c:url value="/user/messages"/>">Messages</a></li>
                    <%--<li><a href="<c:url value="/admin/classRooms"/>">Sale</a></li>--%>
                    <%--<li><a href="<c:url value="/admin/teams"/>">Klasy</a></li>--%>
                    <%--<li><a href="<c:url value="/admin/news"/>">Wiadomości</a></li>--%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form:form action="/logout" method="post">
                <button type="submit" value="Wyloguj" class="btn btn-link">
                    <span class="glyphicon glyphicon-log-in"></span> Log out
                    </form:form>
            </ul>
        </c:if>

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
