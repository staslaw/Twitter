<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 10.09.18
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin teacher form</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Dodawanie nauczyciela</h3>
    <form:form modelAttribute="teacher" method="post">
        <table class="table">
            <tr>
                <td>Imię: </td>
                <td><form:input path="firstName"/></td>
                <td><form:errors path="firstName"/></td>
            </tr>
            <tr>
                <td>Nazwisko: </td>
                <td><form:input path="lastName"/></td>
                <td><form:errors path="lastName"/></td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><form:input path="username"/></td>
                <td><form:errors path="username"/></td>
            </tr>
            <tr>
                <td>Hasło: </td>
                <td><form:input type="password" path="password"/></td>
                <td><form:errors path="password"/></td>
            </tr>
            <tr>
                <td>Przedmioty: </td>
                <td>
                    <c:forEach items="${subjects}" var="subject">
                        <div class="checkbox">
                            <label><input type="checkbox" path="subjects" value="">${subject.name}</label>
                        </div>
                    </c:forEach>
                </td>
                <%--<td><form:select path="subjects" items="${subjects}" itemLabel="name" itemValue="id" multiple="true"/></td>--%>
                <td><form:errors path="subjects"/></td>
            </tr>
            <tr>
                <td>Opis: </td>
                <td><form:input path="description"/></td>
                <td><form:errors path="description"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Dodaj"/></td>
                <td></td>
            </tr>
        </table>
    </form:form>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>
