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


    <c:if test="${list.size() != 0}">
            <table class="table table-striped">
                <c:forEach items="${list}" var="tweet">
                        <tr>
                            <td>${tweet.user.username}</td>
                            <td>${tweet.text}</td>
                        </tr>
                </c:forEach>
            </table>
    </c:if>

    <c:if test="${list.size() == 0}">
        <c:out value="Brak tweetów do wyświetlenia"/>
    </c:if>

</div>
</body>
</html>
