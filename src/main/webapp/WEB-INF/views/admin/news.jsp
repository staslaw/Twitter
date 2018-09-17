<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.09.18
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin news</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Lista newsów</h3>

    <c:if test="${news.size() != 0}">
    <table class="table table-striped">
        <tr>
            <th>Data dodania</th>
            <th>Odbiorcy</th>
            <th>Tytuł wiadomości</th>
            <th>Treść wiadomości</th>
        </tr>
    <c:forEach items="${news}" var="newsik">
        <tr>
            <td>${newsik.date}</td>
            <td><c:forEach items="${newsik.role}" var="rol">
                <c:out value="${rol.name}"/><br>
            </c:forEach></td>
            <td>${newsik.name}</td>
            <td style="word-break: break-all">${newsik.content}</td>
        </tr>
    </c:forEach>
    </table>
    </c:if>
    <c:if test="${news.size() == 0}">
        <c:out value="nie dodałeś jeszcze żadnych wiadomości"/>
    </c:if>

        <a href="news/add" class="btn btn-default">Dodaj wiadomość</a>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
