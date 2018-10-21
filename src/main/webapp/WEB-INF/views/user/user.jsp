<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 05.10.18
  Time: 11:45
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
        <tr height="20">
            <td align="center">
                <h3>last tweets</h3>
            </td>
            <td rowspan="3" align="center">
                <h3>write new tweet!</h3>
                <form:form modelAttribute="tweet" method="POST">
                    <form:textarea rows="5" cols="30" path="text" maxlength="140"/>
                    <input type="submit" value="dodaj"/>
                    <form:errors path="text"/>
                </form:form>
            </td>
        </tr>
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
    </table>

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

</body>
</html>
