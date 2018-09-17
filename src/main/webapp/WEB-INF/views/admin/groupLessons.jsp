<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 11.09.18
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin team lessons</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Lista lekcji klasy ${team}</h3>
    <table class="table table-striped">
        <c:if test="${lessons.size() != 0}">
        <tr>
            <th>Przedmiot</th>
            <th>Nauczyciel</th>
            <%--<th>Edytuj</th>--%>
            <th>Usuń</th>
        </tr>
        </c:if>
        <c:if test="${lessons.size() == 0}">
            <c:out value="brak lekcji do wyświetlenia"/>
        </c:if>
        <c:forEach items="${lessons}" var="lesson">
            <tr>
                <td>${lesson.subject}</td>
                <td>${lesson.teacher}</td>
                <%--<td><a href="updateLesson?idL=${lesson.id}">edytuj</a></td>--%>
                <td><a class="confirm btn btn-success" href="removeLesson?idL=${lesson.id}">usuń</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="addLesson" class="btn btn-default">Dodaj lekcje</a>
</div>


<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
