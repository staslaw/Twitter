<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 04.10.18
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>

<form:form method="post">
    <table class="table" align="center">
        <tr>
            <td>
                <label>Email:</label>
            </td>
            <td>
                <input type="email" name="username"><br>
            </td>
        </tr>
        <tr>
            <td>
                <label>Password:</label>
            </td>
            <td>
                <input type="password" name="password"><br>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" value="Zaloguj">Zaloguj</button>
            </td>
            <td>
                <a href="/log">Sign in</a>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
