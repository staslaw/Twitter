<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.09.18
  Time: 02:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher mark details</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerTeacher.jsp"/>

<div class="container">
    <h3> Szczegóły oceny </h3>
    <table class="table table-striped">
        <tr>
            <td>Imię i nazwisko:</td>
            <td>${mark.student.firstName} ${mark.student.lastName}</td>
        </tr>
        <tr>
            <td>Przedmiot:</td>
            <td>${mark.markSeries.lesson.subject.name}</td>
        </tr>
        <tr>
            <td>Kategoria:</td>
            <td>${mark.markSeries.name}</td>
        </tr>
        <tr>
            <td>Ocena:</td>
            <td>${mark.value}</td>
        </tr>
        <tr>
            <td>Waga:</td>
            <td>${mark.markSeries.weight}</td>
        </tr>
        <tr>
            <td><a class="confirm btn btn-success" href="../${mark.lesson.id}/removeMark/${mark.id}">usuń</a></td>
            <td><input type="button" onclick="display_prompt()" value="zmień ocenę" /></td>
        </tr>
    </table>
</div>

<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>


<script type="text/javascript">
    function display_prompt() {
        var name = prompt("Wprowadź nową ocenę", "");
        if (name <=6 && name >= 1) {
            location.href="update/${mark.id}/" + name;
        }
    }
</script>

</body>
</html>