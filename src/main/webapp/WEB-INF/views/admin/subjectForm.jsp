<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin subject form</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
<h3>Dodawanie przedmiotu</h3>
<form:form modelAttribute="subject" method="post">
    <table class="table">
        <tr>
            <td>Nazwa: </td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
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
