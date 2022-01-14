<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="client.profile.page" var="profile"/>
<fmt:message key="allUsers.firstName" var="name"/>
<fmt:message key="allUsers.lastName" var="surname"/>
<fmt:message key="allUsers.login" var="login"/>
<fmt:message key="allUsers.email" var="email"/>
<fmt:message key="allUsers.role" var="role"/>
<fmt:message key="allUsers.balance" var="balance"/>
<fmt:message key="allUsers.discount" var="discount"/>
<fmt:message key="admin.goToLink" var="goTo"/>
<fmt:message key="admin.updateProfile" var="updateProfile"/>
<fmt:message key="admin.updatePassword" var="updatePassword"/>
<fmt:message key="client.relevantAppointments" var="relevantApp"/>

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
<div class="container" style="height: 80vh; color: black; font-size: 18px">
    <div class="row">
        <dl class="row">
            <dt class="col-sm-3">${role}</dt>
            <dd class="col-sm-9" style="font-size: 20px">${sessionScope.userRole}</dd>

            <dt class="col-sm-3">${name}</dt>
            <dd class="col-sm-9">${sessionScope.userName}</dd>

            <dt class="col-sm-3">${surname}</dt>
            <dd class="col-sm-9">${sessionScope.userLastName}</dd>

            <dt class="col-sm-3">${login}</dt>
            <dd class="col-sm-9">${sessionScope.userLogin}</dd>

            <dt class="col-sm-3">${email}</dt>
            <dd class="col-sm-9">${sessionScope.userEmail}</dd>

            <dt class="col-sm-3">${balance}</dt>
            <dd class="col-sm-9">${sessionScope.userMoney} £</dd>

            <dt class="col-sm-3">${discount}</dt>
            <dd class="col-sm-9">${sessionScope.userDiscount} %</dd>


            <dt class="col-sm-3"></dt>
            <dd class="col-sm-9">
                <dl class="row">
                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/controller?command=go_to_update_profile"
                                                      style="color: midnightblue; font-size: 18px">${updateProfile}</a></dt>
                </dl>
                <dl class="row">
                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/jsp/update-password.jsp"
                                                      style="color: midnightblue; font-size: 18px">${updatePassword}</a></dt>
                </dl>
            </dd>

            <dt class="col-sm-3">---------------------</dt>
            <dd class="col-sm-9"><dl class="row"><dt class="col-sm-4">------------------------</dt></dl></dd>

            <dt class="col-sm-3">${goTo}</dt>
            <dd class="col-sm-9">
<%--                <dl class="row">--%>
<%--                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/controller?command=show_all_users"--%>
<%--                                                      style="color: black; font-size: 18px">${showAllUsers}</a></dt>--%>
<%--                </dl>--%>
                <dl class="row">
                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/controller?command=show_all_relevant_appointments_by_client"
                                                      style="color: midnightblue; font-size: 18px">${relevantApp}</a></dt>
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
