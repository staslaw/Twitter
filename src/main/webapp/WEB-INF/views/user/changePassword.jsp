<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 23.10.18
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">

            <form method="post" action="changePassword">
            <table class="table">
                <tr>
                    <td>
                        new password
                    </td>
                    <td>
                        <input name="password" type="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        confirm password
                    </td>
                    <td>
                        <input name="passwordConfirm" type="password">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="submit">
                    </td>
                </tr>
            </table>
            </form>

        </div>
        <div class="col-sm-2">
        </div>
    </div>
</div>

</body>
</html>
