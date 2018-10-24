<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 16.10.18
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<sec:csrfMetaTags/>--%>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>


<div class="container">

</div>

<div class="container">
    <table class="table">
        <form method="POST" action="/user/photo" enctype="multipart/form-data">
            <tr>
                <td>photo:</td>
                <td><img src="${pageContext.request.contextPath}${user.photoPath}" alt="" width="128" height="128"></td>
                <td><a href="/user/photoRemove">remove photo</a></td>
                <td><input type="file" name="file"/></td>
                <td><input type="submit" value="Submit"/></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </form>


        <form:form modelAttribute="user" method="POST" enctype="multipart/form-data">

        <tr>
            <td>username:</td>
            <td><form:input path="username"/></td>
            <td><form:errors path="username"/></td>
        </tr>
        <tr>
            <td>first name:</td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td>last name:</td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td>email:</td>
            <td><form:input path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td>date of birth:</td>
            <td><form:input type="date" path="dateOfBirth" default="null"/></td>
            <td><form:errors path="dateOfBirth"/></td>
        </tr>
        <tr>
            <td>description:</td>
            <td><form:textarea cols="50" rows="6" path="description"/></td>
            <td><form:errors path="description"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="save"/></td>
            <td></td>
        </tr>
    </table>
    </form:form>
</div>

</body>
</html>
