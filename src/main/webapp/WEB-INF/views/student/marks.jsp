<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 13.09.18
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student marks</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headStudent.jsp"/>

<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th></th>
            <c:forEach items="${markSeries}" var="series">
                <th>
                    <c:out value="${series.name}"/>
                </th>
            </c:forEach>
            <th>Twoja średnia (ważona)</th>
            <th>Klasowa średnia (ważona)</th>
        </tr>
        </thead>
        <tr>
            <c:if test="${markSeries.size() == 0}">
                <c:out value="nauczyciel nie wystawił jeszcze żadnych ocen z tego przedmiotu"/>
            </c:if>
            <c:if test="${markSeries.size() != 0}">
            <th>${markSeries.get(0).lesson.subject.name}</th>
            <c:forEach items="${markSeries}" var="series">
                <td>
                    <c:forEach items="${marks}" var="mark">
                        <c:if test="${mark.markSeries == series}">
                            <c:out value="${mark.value}"/>
                        </c:if>
                    </c:forEach>
                </td>
            </c:forEach>
                <th>${avg}</th>
                <th>${avgTeam}</th>
            </c:if>
        </tr>
    </table>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
