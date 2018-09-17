<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 11.09.18
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student home</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headStudent.jsp"/>

<div class="container">
    <h3>Witaj ${student.firstName}</h3>
    <c:if test="${news.size() != 0}">
        <div class="panel-group" style="word-break: break-all">
            <c:forEach items="${news}" var="newsik">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse${newsik.id}">${newsik.name}</a>
                            (${newsik.date})
                        </h4>
                    </div>
                    <div id="collapse${newsik.id}" class="panel-collapse collapse">
                        <div class="panel-body">${newsik.content}</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <%--<c:if test="${news.size() != 0}">
        <table class="table table-striped">
            <tr>
                <th>Data dodania</th>
                <th>Tytuł wiadomości</th>
                <th>Treść wiadomości</th>
            </tr>
            <c:forEach items="${news}" var="newsik">
                <tr>
                    <td>${newsik.date}</td>
                    <td>${newsik.name}</td>
                    <td>${newsik.content}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>--%>
    <c:if test="${news.size() == 0}">
        <c:out value="Brak wiadomości do wyświetlenia"/>
    </c:if>

</div>


<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
