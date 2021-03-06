<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="signUp.page" var="title"/>
<fmt:message key="signUp.page.login" var="login"/>
<fmt:message key="login.helper" var="loginHelper"/>
<fmt:message key="signUp.page.password" var="password"/>
<fmt:message key="password.helper" var="passwordHelper"/>
<fmt:message key="signUp.page.conformPassword" var="confirm"/>
<fmt:message key="signUp.page.firstName" var="name"/>
<fmt:message key="name.helper" var="nameHelper"/>
<fmt:message key="signUp.page.lastName" var="surname"/>
<fmt:message key="lastName.helper" var="lastNameHelper"/>
<fmt:message key="signUp.page.email" var="email"/>

<fmt:message key="signUp.page.valid" var="good"/>
<fmt:message key="signUp.page.invalid" var="bad"/>

<fmt:message key="signUp.button" var="signUp"/>

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
    <div class="row">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_up">
            <div class="form-row">
                <div class="col-md-6 mb-3">
                    <label for="validationServer01">${login}</label>
                    <input type="text" name="login" class="form-control" id="validationServer01"
                           required pattern="[A-Za-z0-9]{3,20}"
                           value="${requestScope.formData['login']}">
                    <small id="passwordHelpBlock0" class="form-text text-muted">
                        ${loginHelper}
                    </small>

                </div>
                <div class="col-md-3 mb-3">
                    <label for="validationServer02">${password}</label>
                    <input type="password" name="password" class="form-control" id="validationServer02"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                           value="${requestScope.formData['password']}">
                    <small id="passwordHelpBlock" class="form-text text-muted">
                        ${passwordHelper}
                    </small>

                </div>
                <div class="col-md-3 mb-3">
                    <label for="validationServer002">${confirm}</label>
                    <input type="password" name="repeatPassword" class="form-control" id="validationServer002"
                           required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}"
                           value="${requestScope.formData['repeatPassword']}">

                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6 mb-3">
                    <label for="validationServer03">${name}</label>
                    <input type="text" name="firstName" class="form-control" id="validationServer03"
                           required pattern="^[A-Z??-??]{1}[a-z??-??]{2,20}$"
                           value="${requestScope.formData['firstName']}">
                    <small id="passwordHelpBlockF" class="form-text text-muted">
                        ${nameHelper}
                    </small>

                </div>
                <div class="col-md-3 mb-3">
                    <label for="validationServer04">${surname}</label>
                    <input type="text" name="lastName" class="form-control"
                           id="validationServer04" required
                           pattern="^[A-Z??-??]{1}[a-z??-??]{2,20}$"
                           value="${requestScope.formData['lastName']}">
                    <small id="passwordHelpBlockL" class="form-text text-muted">
                        ${lastNameHelper}
                    </small>

                </div>
                <div class="col-md-3 mb-3">
                    <label for="validationServer05">${email}</label>
                    <input type="email" name="email" class="form-control" id="validationServer05" required
                           pattern="^(?=.{1,45}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$"
                           value="${requestScope.formData['email']}">
                </div>
            </div>

            <div class="row">
                <c:if test="${requestScope.errorMessage != null}">
                    <div class="form-group">
                        <div class="col-md-6 mb-3">
                            <div class="err-message-from-server">
                                <fmt:setBundle basename="bundle/locale" var="rb"/>
                                <fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>

            <button class="btn btn-primary" type="submit">${signUp}</button>
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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
</body>
</html>
