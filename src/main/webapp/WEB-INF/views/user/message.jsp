<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 22.10.18
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>

<table class="container">
    <form method="POST" action="/user/message">
        <tr>
            <td align="center">
                <input hidden="true" type="number" value="${userToId}" name="userToId"><br>
                <textarea rows="10" cols="50" name="text"></textarea><br>
            </td>
        </tr>
        <tr>
            <td align="center">
                <input type="submit" value="send message">
            </td>
        </tr>
    </form>
</table>

</body>
</html>
