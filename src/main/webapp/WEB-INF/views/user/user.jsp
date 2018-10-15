<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 05.10.18
  Time: 11:45
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
    <h3>Witaj ${user.username}</h3>

    <form:form modelAttribute="tweet" method="POST">
        <table>
            <tr>
                <td><form:textarea rows="5" cols="50" path="text" maxlength="140"/></td>
                <td><input type="submit" value="dodaj"/></td>

            </tr>
            <tr>
                <td><form:errors path="text"/></td>
                <td></td>
            </tr>
        </table>
    </form:form>


    <table class="table table-striped">
        <c:forEach items="${tweetsPage.content}" var="tweet">
            <tr>
                <td>${tweet.user.username}</td>
                <td>${tweet.text}</td>
            </tr>
        </c:forEach>
    </table>

    <%--<ul class="nav nav-pills">--%>
        <%--<li class="nav-item">--%>
            <%--<c:forEach items="${numbers.sequence(0, tweetsPage.totalPages - 1)}" var="i">--%>
                <%--<a href="/user/main?page=${i}" class="nav-link">${i}</a>--%>
            <%--</c:forEach>--%>
        <%--</li>--%>
    <%--</ul>--%>

    <ul class="pagination">
        <li><a href="/user/main?page=0">&laquo;</a></li>
        <c:forEach var = "i" begin = "0" end = "${tweetsPage.totalPages-1}">
            <li><a href="/user/main?page=${i}">${i+1}</a></li>
        </c:forEach>
        <li><a href="/user/main?page=${tweetsPage.totalPages-1}">&raquo;</a></li>
    </ul>

    <%--<ul class="pager">--%>
        <%--<li><a href="#">&larr; Poprzednia strona</a></li>--%>
        <%--<li><a href="#">Następna strona &rarr;</a></li>--%>
    <%--</ul>--%>

</div>
</body>
</html>
