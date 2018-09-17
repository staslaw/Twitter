<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher lessons</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerTeacher.jsp"/>

<div class="container">
    <h3> Twoje lekcje </h3>
    <table class="table table-striped">
        <c:if test="${lessons.size() != 0}">
        <tr>
            <th>Przedmiot</th>
            <th>Klasa</th>
            <th>Oceny</th>
        </tr>
        </c:if>
        <c:if test="${lessons.size() == 0}">
            <c:out value="Brak lekcji do wyświetlenia"/>
        </c:if>

        <c:forEach items="${lessons}" var="lesson">
            <tr>
                <td>${lesson.subject}</td>
                <td>${lesson.team}</td>
                <td><a href="marks/${lesson.id}">Oceny</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>