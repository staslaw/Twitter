<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher lessons</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerTeacher.jsp"/>

<div class="container">
    <h3> Oceny z ${lesson.subject.name} klasy ${lesson.team.name} </h3>
    <table class="table table-condensed">
        <c:if test="${students.size() != 0}">
            <thead>
                <tr>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <c:forEach items="${markSeries}" var="markser">
                        <th>${markser.name}</th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
        </c:if>
        <c:if test="${students.size() == 0}">
            <c:out value="w tej klasie nie ma uczniów"/>
        </c:if>

        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <c:forEach items="${markSeries}" var="markser">
                    <td>
                        <c:set var="ocena"/>
                        <c:forEach items="${marksAll}" var="marksall">
                            <c:if test="${marksall.markSeries.id == markser.id}">
                                <c:if test="${marksall.student.id == student.id}">
                                    <c:set var="ocena" value="${marksall.value}"/>
                                    <a href='details/${marksall.id}'>${ocena}</a>
                                </c:if>
                            </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${ocena <= 0}">
                                <form:form modelAttribute="mark" method="post">
                                    <form:checkbox path="markSeries" value="${markser}" checked="true" hidden="true"/>
                                    <form:checkbox path="student" value="${student}" checked="true" hidden="true"/>
                                    <form:checkbox path="lesson" value="${lesson}" checked="true" hidden="true"/>
                                    <form:input path="value"/>
                                </form:form>
                            </c:when>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        <c:if test="${markSeries.size() != 0}">
            <tr>
                <th>usuń serię</th>
                <td></td>
                <c:forEach items="${markSeries}" var="marks">
                    <td><a class="confirm btn btn-success" href="${lesson.id}/remove/${marks.id}">usuń</a></td>
                </c:forEach>
            </tr>
            <tr>
                <th>edytuj serię</th>
                <td></td>
                <c:forEach items="${markSeries}" var="marks">
                    <td><a href="${lesson.id}/update/${marks.id}">edytuj</a></td>
                </c:forEach>
            </tr>
        </c:if>
            </tbody>
    </table>

    <br>
    <c:if test="${students.size() != 0}">
        <a href="${lesson.id}/addSeries" class="btn btn-default">Dodaj serię ocen</a>
    </c:if>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>