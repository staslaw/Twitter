<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin student form</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Dodawanie ucznia</h3>
    <form:form modelAttribute="student" method="POST">
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
                <td>Klasa: </td>
                <td><form:select path="team" items="${groups}" itemLabel="name" itemValue="id"/></td>
                <td><form:errors path="team"/></td>
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
