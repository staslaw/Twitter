<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 13.09.18
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student lessons</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headStudent.jsp"/>

<div class="container">
    <c:if test="${lessons == null}">
        Twoja klasa nie ma przypisanych żadnych lekcji
    </c:if>
    <c:if test="${lessons != null}">

    <table class="table table-striped">
        <tr>
            <th>Przedmiot</th>
            <th>Twoja średnia (ważona)</th>
            <th>Klasowa średnia (ważona)</th>
        </tr>
        <c:forEach items="${lessons}" var="lesson">
            <tr>
                <td><a href="marks/${lesson.id}">${lesson.subject.name}</a></td>
                <td>tu będzie średnia</td>
                <td>i tu też</td>
            </tr>
        </c:forEach>

    </table>

    </c:if>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
