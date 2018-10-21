<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 21.10.18
  Time: 11:01
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
        <div class="col-sm-4" style="background-color:green;">
            <table class="table">
                <tr>
                    <td>photo:</td>
                    <td><img src="${pageContext.request.contextPath}${other.photoPath}" alt="" width="128" height="128">
                    </td>
                </tr>
                <tr>
                    <td>username:</td>
                    <td><c:out value="${other.username}"/></td>
                </tr>
                <tr>
                    <td>first name:</td>
                    <td><c:out value="${other.firstName}"/></td>
                </tr>
                <tr>
                    <td>last name:</td>
                    <td><c:out value="${other.lastName}"/></td>
                </tr>
                <tr>
                    <td>email:</td>
                    <td><c:out value="${other.email}"/></td>
                </tr>
                <tr>
                    <td>date of birth:</td>
                    <td><c:out value="${other.dateOfBirth}"/></td>
                </tr>
                <tr>
                    <td>description:</td>
                    <td><c:out value="${other.description}"/></td>
                </tr>
            </table>
        </div>

        <div class="col-sm-8" style="background-color:orange;">
            <c:forEach items="${tweetsPage.content}" var="tweet">
                <tr>
                    <td>
                        <div class="media">
                            <div class="media-left">
                                <img src=${tweet.user.photoPath} class="media-object" style="width:60px">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading"><a style="text-decoration: none;"
                                                             href="/user/otherUser?id=${tweet.user.id}">${tweet.user.username}</a><small><i> ${tweet.createdDate} ${tweet.createdTime}</i></small></h4>
                                <p>
                                    <a style="text-decoration: none;"
                                       href="/user/tweetDetails?id=${tweet.id}">${tweet.text}</a><br>
                                    <a style="text-decoration: none;"><small><i>liczba komentarzy: ${tweet.comments.size()}</i></small></a>
                                </p>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </div>

        <c:if test="${tweetsPage.totalPages} != 0">
            <ul class="pagination">
                <li><a href="/user/main?page=0">&laquo;</a></li>
                <c:forEach var="i" begin="0" end="${tweetsPage.totalPages-1}">
                    <li><a href="/user/main?page=${i}">${i+1}</a></li>
                </c:forEach>
                <li><a href="/user/main?page=${tweetsPage.totalPages-1}">&raquo;</a></li>
            </ul>
        </c:if>

    </div>
</div>

</body>
</html>
