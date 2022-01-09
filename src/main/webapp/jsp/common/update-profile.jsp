<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="update.profile.page" var="title"/>
<fmt:message key="allUsers.firstName" var="name"/>
<fmt:message key="allUsers.lastName" var="surname"/>
<fmt:message key="allUsers.login" var="login"/>
<fmt:message key="allUsers.email" var="email"/>
<fmt:message key="update.profile.button" var="button"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <script type="text/javascript">
        window.history.forward();

        function noBack() {
            window.history.forward();
        }
    </script>
    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<c:import url="../fragment/header.jsp"/>
<br/>
<div class="container" style="height: 66vh">
    <div class="row" style="justify-content: center">
        <form style="justify-content: center" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_profile">
            <div class="form-group">
                <label for="exampleInputPassword0">${name}</label>
                <input type="text" class="form-control" id="exampleInputPassword0" aria-describedby="emailHelp"
                       name="firstName" value="${requestScope.userToUpdate.firstName}" required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">${surname}</label>
                <input type="text" class="form-control" id="exampleInputPassword1"
                       name="lastName" value="${requestScope.userToUpdate.lastName}" required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
<%--                <small id="emailHelp" class="form-text text-muted">${helper}</small>--%>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword2">${login}</label>
                <input type="text" class="form-control" id="exampleInputPassword2"
                       name="login" value="${requestScope.userToUpdate.login}" required pattern="[A-Za-z0-9]{3,20}">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail3">${email}</label>
                <input type="text" class="form-control" id="exampleInputEmail3"
                       name="email" value="${requestScope.userToUpdate.email}"
<%--                       required pattern="(?=^.{1,100}$)([A-Za-z0-9._-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,})">--%>
                       required pattern="^(?=.{1,45}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$">

<%--                       required pattern="[_0-9a-z][-_.0-9a-z]*@[0-9a-z][-.0-9a-z]*[0-9a-z]\.[a-z]{2,}">--%>
            </div>
            <c:if test="${requestScope.errorMessage != null}">
                <div class="form-group form-check" style="color: red">
                    <input type="hidden" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">
                        <fmt:setBundle basename="bundle/locale" var="rb"/>
                        <fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/>
                    </label>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary"
                    style="color: darkslategray; border-color: darkslategray">${button}</button>
        </form>
    </div>
</div>

<c:import url="../fragment/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
</body>
</html>
