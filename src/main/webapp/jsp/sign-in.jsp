<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 16/12/2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="signIn.page" var="title"/>
<fmt:message key="signIn.page.login" var="login"/>
<fmt:message key="signIn.page.invalidLogin" var="invalidLogin"/>
<fmt:message key="signIn.page.password" var="password"/>
<fmt:message key="signIn.page.invalidPassword" var="invalidPassword"/>
<fmt:message key="signIn.buttonLogin" var="buttonLogin"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">

    <SCRIPT type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </SCRIPT>

    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<div class="sign" style="height: 67vh">
    <c:import url="header.jsp"/>

        <div class="container" style="height: inherit">
            <div class="row">
                <form class="row g-3 needs-validation" novalidate
                      action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="sign_in">

                    <div class="col-md-4">
                        <label for="validationCustom01" class="form-label">${login}</label>
                        <input type="text" name="login" class="form-control" id="validationCustom01" required
                               pattern="[A-Za-z0-9]{3,20}">
                        <div class="invalid-feedback">
                            ${invalidLogin}
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label for="validationCustom02" class="form-label">${password}</label>
                        <input type="password" name="password" class="form-control" id="validationCustom02" required
                               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                        <div class="invalid-feedback">
                            ${invalidPassword}
                        </div>
                    </div>
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
                    <br>
                    <div class="col-12">
                        <button class="btn btn-primary mt-4" type="submit">${buttonLogin}</button>
                    </div>

                </form>
            </div>
        </div>

    <c:import url="footer.jsp"/>
</div>
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
