<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin class room form</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
<h3>Dodawanie sali</h3>
    <form:form modelAttribute="classRoom" method="post">
        <table class="table">
            <tr>
                <td>Numer: </td>
                <td><form:input path="number"/></td>
                <td><form:errors path="number" class="alert alert-danger"/></td>
            </tr><tr>
                <td>Przedmiot: </td>
                <td><form:select path="subject" items="${subjects}" itemLabel="name" itemValue="id"/></td>
                <td><form:errors path="subject"/></td>
            </tr><tr>
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
