<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 05.10.18
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>


<body>
<form:form modelAttribute="user" method="POST">
    <table class="table">
        <tr>
            <td>Username: </td>
            <td><form:input path="username"/></td>
            <td><form:errors path="username"/></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><form:input path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>

        <tr>
            <td>Password: </td>
            <td><form:input path="password" maxlength="20"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Sign in"/></td>
            <td></td>
        </tr>
    </table>
</form:form>

</body>
</html>
