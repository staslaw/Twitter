<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Groups</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Lista klas</h3>
    <table class="table table-striped">
        <c:if test="${teams.size() != 0}">
            <thead>
                <tr>
                    <th>Oznaczenie</th>
                    <th>Wychowawca</th>
                    <th>Szczegóły</th>
                    <th>Usuń</th>
                </tr>
            </thead>
        </c:if>
        <c:if test="${teams.size() == 0}">
            <c:out value="brak klas do wyświetlenia"/>
        </c:if>

        <c:forEach items="${teams}" var="team">
            <tr>
                <td><c:out value="${team.name}"/></td>
                <td><c:out value="${team.educator}"/></td>
                <td><a href="teams/${team.id}/details">szczegóły</a></td>
                <td><a class="confirm btn btn-success" href="teams/${team.id}/remove">usuń</a></td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="teams/add" class="btn btn-default">Dodaj klasę</a>
</div>
<br>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
