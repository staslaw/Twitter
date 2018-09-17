<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Strona startowa</title>
</head>
<body>
<jsp:include page="../../WEB-INF/fragments/header.jsp"/>
<div class="container" style="margin:30px">
    <form:form method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="username">
        </div>
        <div class="form-group">
            <label for="pwd">Hasło:</label>
            <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <button type="submit" class="btn btn-default" value="Zaloguj">Zaloguj</button>
    </form:form>
</div>
<jsp:include page="../../WEB-INF/fragments/footer.jsp"/>
</body>
</html>