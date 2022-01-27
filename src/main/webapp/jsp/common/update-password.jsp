<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="updatePassword.page" var="title"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="enter.current" var="current"/>
<fmt:message key="enter.new.password" var="newPassword"/>
<fmt:message key="enter.repeat" var="repeat"/>
<fmt:message key="password.helper" var="helper"/>

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
            <input type="hidden" name="command" value="update_password">
            <div class="form-group">
                <label for="exampleInputPassword3">${current}</label>
                <input type="password" class="form-control" id="exampleInputPassword3" aria-describedby="emailHelp"
                       name="oldPassword" value="${requestScope.oldPassword}" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">${newPassword}</label>
                <input type="password" class="form-control" id="exampleInputPassword1"
                name="newPassword" value="${requestScope.newPassword}" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
                <small id="emailHelp" class="form-text text-muted">${helper}</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword2">${repeat}</label>
                <input type="password" class="form-control" id="exampleInputPassword2"
                       name="repeatPassword" value="${requestScope.repeatPassword}" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
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
                    style="color: darkslategray; border-color: darkslategray">${update}</button>
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
