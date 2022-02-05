<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="addAdmin.page" var="title"/>
<fmt:message key="signUp.page.login" var="login"/>
<fmt:message key="update.loginHelper" var="loginHelper"/>
<fmt:message key="signUp.page.password" var="password"/>
<fmt:message key="password.helper" var="passwordHelper"/>
<fmt:message key="signUp.page.conformPassword" var="confirm"/>
<fmt:message key="signUp.page.firstName" var="name"/>
<fmt:message key="update.nameHelper" var="nameHelper"/>
<fmt:message key="signUp.page.lastName" var="surname"/>
<fmt:message key="signUp.page.email" var="email"/>

<fmt:message key="addAdminPiercer.button" var="add"/>

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

<div class="container" style="height: 80vh">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="add_admin">

        <div class="form-group">
            <label for="exampleFormControlInput2">${login}</label>
            <input type="text" class="form-control" id="exampleFormControlInput2" name="login"
                   required pattern="[A-Za-z0-9]{3,20}"
                   value="${requestScope.formData['login']}">
            <small id="emailHelp3" style="color: black" class="form-text text-muted">${loginHelper}</small>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput4">${password}</label>
            <input type="password" class="form-control" id="exampleFormControlInput4"
                   name="password"
                   required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                   value="${requestScope.formData['password']}">
            <small id="passwordHelpBlock0" style="color: black" class="form-text text-muted">
                ${passwordHelper}
            </small>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput5">${confirm}</label>
            <input type="password" class="form-control" id="exampleFormControlInput5"
                   name="repeatPassword"
                   required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                   value="${requestScope.formData['repeatPassword']}">
            <small id="passwordHelpBlock1" style="color: black" class="form-text text-muted">
                ${passwordHelper}
            </small>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput6">${name}</label>
            <input type="text" class="form-control" id="exampleFormControlInput6"
                   name="firstName"
                   required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$"
                   value="${requestScope.formData['firstName']}">
            <small id="emailHelp2" style="color: black" class="form-text text-muted">${nameHelper}</small>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput7">${surname}</label>
            <input type="text" class="form-control" id="exampleFormControlInput7"
                   name="lastName"
                   required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$"
                   value="${requestScope.formData['lastName']}">
            <small id="emailHelp4" style="color: black" class="form-text text-muted">${nameHelper}</small>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput8">${email}</label>
            <input type="email" class="form-control" id="exampleFormControlInput8"
                   name="email" required
                   pattern="^(?=.{1,45}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$"
                   value="${requestScope.formData['email']}">
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
        <div class="form-group col-md-10" style="justify-content: center">
            <button type="submit" class="btn btn-primary"
                    style="background-color: forestgreen; border-color: forestgreen; justify-content: center">${add}</button>
        </div>
    </form>
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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
</body>
</html>
