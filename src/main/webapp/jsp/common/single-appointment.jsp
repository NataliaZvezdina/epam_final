<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="single.appointment.datetime" var="datetime"/>
<fmt:message key="relevantAppointments.facility" var="facility"/>
<fmt:message key="single.appointment.price" var="price"/>
<fmt:message key="allUsers.lastName" var="lastName"/>
<fmt:message key="single.appointment.client" var="client"/>
<fmt:message key="single.appointment.piercer" var="piercer"/>
<fmt:message key="single.appointment.notes" var="notes"/>
<fmt:message key="single.appointment" var="cancell"/>


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

<div class="container" style="height: 80vh; color: black; font-size: 18px">
    <div class="row">
        <dl class="row">
            <dt class="col-sm-3">${datetime}</dt>
            <dd class="col-sm-9" style="font-size: 20px">${requestScope.appointment.dateTime}</dd>

            <dt class="col-sm-3">${facility}</dt>
            <dd class="col-sm-9">${requestScope.facility.name}</dd>

            <c:if test="${sessionScope.userRole == 'CLIENT'}">
                <dt class="col-sm-3">${price}</dt>
                <dd class="col-sm-9">${requestScope.facility.price}</dd>
                <dt class="col-sm-3">${piercer}</dt>
                <dd class="col-sm-9">${requestScope.fullName}</dd>
            </c:if>

            <c:if test="${sessionScope.userRole == 'PIERCER'}">
                <dt class="col-sm-3">${client}</dt>
                <dd class="col-sm-9">${requestScope.lastName} ${requestScope.firstName}</dd>
            </c:if>

            <dt class="col-sm-3">${notes}</dt>
            <dd class="col-sm-9">${requestScope.appointment.notes}</dd>

            <dt class="col-sm-3">---------------------</dt>
            <dd class="col-sm-9">
                <dl class="row">
                    <dt class="col-sm-4">------------------------</dt>
                </dl>
            </dd>

            <dt class="col-sm-3"></dt>
            <dd class="col-sm-9">
                <c:if test="${sessionScope.userRole == 'CLIENT' || sessionScope.userRole == 'ADMIN'}">
                    <dl class="row">
                        <dt class="col-sm-4">&#9679;
                            <a href="${pageContext.request.contextPath}/controller?command=cancel_appointment&appointmentId=${requestScope.appointment.appointmentId}"
                               style="color: midnightblue; font-size: 18px">${cancell}</a></dt>
                    </dl>
                </c:if>
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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
</body>
</html>
