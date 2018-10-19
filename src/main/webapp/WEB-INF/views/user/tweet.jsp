<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 19.10.18
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/header.jsp"/>

<div class="container">
    <table class="table table-striped">
        <tr>
            <td>
                <div class="media">
                    <div class="media-left">
                        <img src=${tweet.user.photoPath} class="media-object" style="width:60px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${tweet.user.username} <small><i> ${tweet.createdDate} ${tweet.createdTime}</i></small></h4>
                        <p>${tweet.text}</p>

                        <c:forEach items="${comments}" var="comment">
                        <div class="media">
                            <div class="media-left">
                                <img src=${comment.user.photoPath} class="media-object" style="width:60px">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${comment.user.username} <small><i> ${comment.createdDate} ${comment.createdTime}</i></small></h4>
                                <p>${comment.text}</p>
                            </div>
                        </div>
                        </c:forEach>


                    </div>
                </div>
            </td>
            <td></td>
            <td rowspan="3" align="center">
                <h3>write new comment!</h3>
                <form action="/user/tweetDetails" method="POST">
                    <textarea rows="3" cols="30" type="text" maxlength="60" name="text"></textarea>
                    <input type="number" name="tweetId" value="${tweet.id}" hidden="true"/>
                    <input type="submit" value="dodaj"/>
                </form>
            </td>
        </tr>

    </table>
</div>

</body>
</html>
