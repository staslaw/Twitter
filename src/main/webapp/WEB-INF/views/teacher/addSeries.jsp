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
<h3> Dodawanie klasie ${lesson.team.name} serii ocen z przedmiotu ${lesson.subject.name} </h3>
<form:form modelAttribute="markSeries" method="post">
    <table class="table">
        <tr>
            <td>Nazwa: </td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <tr>
            <td>Waga: </td>
            <td><form:input type="number" path="weight"/></td>
            <td><form:errors path="weight"/></td>
        </tr>
        <tr>
            <td><form:checkbox path="lesson" value="${lesson}" checked="true" hidden="true"/></td>
            <td><input type="submit" value="Dodaj"/></td>
            <td><form:errors path="lesson"/></td>
        </tr>
    </table>
</form:form>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>