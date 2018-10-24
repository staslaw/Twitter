<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 22.10.18
  Time: 12:24
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
        <div class="col-sm-4">
            <h2>messages received</h2>
        </div>
        <div class="col-sm-4">
            <h2>messages sent</h2>
        </div>
        <div class="col-sm-2">
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-4">
            <c:forEach items="${messagesGot}" var="got">
                <div class="media">
                    <div class="media-left">
                        <img src=${got.fromUser.photoPath} class="media-object" style="width:60px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><a style="text-decoration: none;"
                                                     href="/user/profile?id=${got.fromUser.id}">${got.fromUser.username}</a>
                            <small><i> ${got.createdDate} ${got.createdTime}</i></small>
                        </h4>
                        <p>${got.text}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-sm-4">
            <c:forEach items="${messagesSent}" var="sent">
                <div class="media">
                    <div class="media-left">
                        <img src=${sent.toUser.photoPath} class="media-object" style="width:60px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><a style="text-decoration: none;"
                                                     href="/user/profile?id=${sent.toUser.id}">${sent.toUser.username}</a>
                            <small><i> ${sent.createdDate} ${sent.createdTime}</i></small>
                        </h4>
                        <p>${sent.text}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-sm-2">
        </div>
    </div>
</div>


</body>
</html>
