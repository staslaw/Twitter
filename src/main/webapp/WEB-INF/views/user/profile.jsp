<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.10.18
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>



<div class="container">
    <table class="table">
        <tr>
            <td>photo: </td>
            <td><img src="${pageContext.request.contextPath}${user.photoPath}" alt="" width="128" height="128"></td>
        </tr>
        <tr>
            <td>username: </td>
            <td><c:out value="${user.username}"/></td>
        </tr>
        <tr>
            <td>first name: </td>
            <td><c:out value="${user.firstName}"/></td>
        </tr>
        <tr>
            <td>last name: </td>
            <td><c:out value="${user.lastName}"/></td>
        </tr>
        <tr>
            <td>email: </td>
            <td><c:out value="${user.email}"/></td>
        </tr>
        <tr>
            <td>date of birth: </td>
            <td><c:out value="${user.dateOfBirth}"/></td>
        </tr>
        <tr>
            <td>description: </td>
            <td><c:out value="${user.description}"/></td>
        </tr>
    </table>

    <a href="/user/update" class="btn btn-default">edit</a><br><br>
    <a href="/user/changePassword" class="btn btn-default">change password</a>
</div>
</body>
</html>
