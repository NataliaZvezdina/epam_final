<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="client.topUpBalance" var="title"/>
<fmt:message key="client.message" var="message"/>
<fmt:message key="client.button" var="button"/>

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

<div class="container" style="height: 64vh">
    <br/><br/><br/>
    <c:if test="${requestScope.errorMessage != null}">
        <div class="form-group">
            <div class="col-md-6 mb-3">
                <div class="err-message-from-server" style="text-align: center; color: red">
                    <fmt:setBundle basename="bundle/locale" var="rb"/>
                    <fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/>
                </div>
            </div>
        </div>
    </c:if>
    <br/><br/>
    <form action="${pageContext.request.contextPath}/controller" method="post"
          class="form-inline" style="justify-content: center">
        <input type="hidden" name="command" value="top_up_balance">
        <div class="form-group mb-2">
            <label for="staticEmail2" class="sr-only">${message}</label>
            <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="${message}">
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword2" class="sr-only"></label>
            <input type="number" name="moneyToAdd" step="0.01" min="0" max="99.99"
                   class="form-control" id="inputPassword2" placeholder="0.0" required
                   pattern="^[0-9]{1,2}(\.[0-9]{1,2})?$">
        </div>
        <button type="submit" class="btn btn-primary mb-2">${button}</button>
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
