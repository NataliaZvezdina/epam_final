<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="client.profile.page" var="profile"/>

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
    <title>${profile}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<c:import url="../fragment/header.jsp"/>

<div class="container" style="color: black; height: 67vh">
    <div class="row">
        <dl class="row">
            <dt class="col-sm-3">id</dt>
            <dd class="col-sm-9">A description list is perfect for defining terms.</dd>

            <dt class="col-sm-3">Term</dt>
            <dd class="col-sm-9">
                <p>Definition for the term.</p>
                <p>And some more placeholder definition text.</p>
            </dd>

            <dt class="col-sm-3">Another term</dt>
            <dd class="col-sm-9">This definition is short, so no extra paragraphs or anything.</dd>

            <dt class="col-sm-3 text-truncate">Truncated term is truncated</dt>
            <dd class="col-sm-9">This can be useful when space is tight. Adds an ellipsis at the end.</dd>

            <dt class="col-sm-3">Look through</dt>
            <dd class="col-sm-9">
                <dl class="row">
                    <dt class="col-sm-4">All orders</dt>
<%--                    <dd class="col-sm-8">I heard you like definition lists. Let me put a definition list inside your definition list.</dd>--%>
                </dl>
                <dl class="row">
                    <dt class="col-sm-4">All active appointments</dt>
                </dl>
            </dd>
        </dl>
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
