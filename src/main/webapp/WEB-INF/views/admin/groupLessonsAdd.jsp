<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 11.09.18
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin group lesson add</title>
</head>
<body>
<jsp:include page="../../../WEB-INF/fragments/headerAdmin.jsp"/>

<div class="container">
    <h3>Dodawanie lekcji klasie ${team.name}</h3>
    <form:form modelAttribute="lesson" method="POST">
        <table class="table">
            <tr>
                <td>Wybierz przedmiot: </td>
                <td><form:select required="true" path="subject" id="subject">
                    <form:option value="" label=" -- Select -- "/>
                    <form:options items="${subjectsNew}" itemValue="id" itemLabel="name"/>
                </form:select></td>
                <td><form:errors path="subject"/></td>
            </tr>
            <tr>
                <td>Wybierz nauczyciela: </td>
                <td><form:select required="true" path="teacher" items="${teachers}" itemValue="id" itemLabel="lastName" id="teacher"/></td>
                <td><form:errors path="teacher"/></td>
            </tr>
            <tr>
                <td><form:checkbox path="team" value="${team.id}" checked="true" hidden="true"/></td>
                <td><input type="submit" value="Dodaj"/></td>
                <td><form:errors path="team" hidden="true"/></td>
            </tr>
        </table>
    </form:form>
</div>


<jsp:include page="../../../WEB-INF/fragments/footer.jsp"/>



<script
        src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous">
</script>
<script type="text/javascript">
    var selectedSubjectId;
    $("#subject").on("change", function () {
        selectedSubjectId = $(this).val();
        loadBudgets();
    });

    var budgetList = $('#teacher');
    var loadBudgets = function () {
        $.ajax({
            url: "/api/subject/" + selectedSubjectId,
            dataType: "json",
            type: "GET"
        })
            .done(function (items){
                budgetList.html("").append($("<option></option>"));
                items.forEach(item => {
                    $("<option value='" + item.id + "'>" + item.firstName + " " + item.lastName + "</option>").appendTo(budgetList);
            })
            });
    };
</script>
</body>
</html>
