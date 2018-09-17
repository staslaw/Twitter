<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 09.09.18
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin group form</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Dodawanie klasy</h3>
    <form:form modelAttribute="team" method="post">
        <table class="table">
            <tr>
                <td>Oznaczenie: </td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name"/></td>
            </tr>
            <tr>
                <td>Wychowawca: </td>
                <td><form:select path="educator" items="${teachers}" itemLabel="lastName" itemValue="id"/></td>
                <td><form:errors path="educator"/></td>
            </tr>
            <%--<tr>--%>
                <%--<td>Subject list: </td>--%>
                <%--<td><form:checkboxes path="subjects" items="${subjects}" itemLabel="name" itemValue="id"/></td>--%>
                <%--<td><form:errors path="subjects"/></td>--%>
            <%--</tr>--%>
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
