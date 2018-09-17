<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.09.18
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin news form</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Dodawanie wiadomości</h3>
    <form:form modelAttribute="news" method="POST">
        <table class="table">
            <tr>
                <td>Tytuł: </td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name"/></td>
            </tr>
            <tr>
                <td>Treść wiadomości: </td>
                <td><form:textarea path="content" cols="100" rows="10"/></td>
                <td><form:errors path="content"/></td>
            </tr>
            <tr>
                <td>Odbiorcy: </td>
                <td><form:select path="role" items="${roles}" itemValue="id" itemLabel="name"/></td>
                <td><form:errors path="role"/></td>
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
