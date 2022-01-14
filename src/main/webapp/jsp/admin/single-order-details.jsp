<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="singleOrder.page" var="title"/>
<fmt:message key="singleOrder.orderId" var="orderId"/>
<fmt:message key="singleOrder.date" var="date"/>
<fmt:message key="singleOrder.status" var="status"/>

<fmt:message key="jewelry.image" var="image"/>
<fmt:message key="jewelry.type" var="type"/>
<fmt:message key="jewelry.description" var="description"/>
<fmt:message key="jewelry.manufacturer" var="manufacturer"/>
<fmt:message key="basket.quantity" var="quantity"/>
<fmt:message key="basket.pricePerUnit" var="unitPrice"/>
<fmt:message key="singleOrder.clientInfo" var="clientInfo"/>
<fmt:message key="singleOrder.discount" var="discount"/>
<fmt:message key="basket.totalCost" var="totalCost"/>
<fmt:message key="singleOrder.content" var="content"/>

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

<div class="container" style="color: black; font-size: 18px">
    <div class="row">
        <dl class="row">
            <dt class="col-sm-3">${orderId}</dt>
            <dd class="col-sm-9" style="font-size: 20px">${requestScope.singleOrder.orderId}</dd>

            <dt class="col-sm-3">${date}</dt>
            <dd class="col-sm-9">${requestScope.singleOrder.date}</dd>

            <dt class="col-sm-3">${clientInfo}</dt>
            <dd class="col-sm-9">${requestScope.client.firstName}  ${requestScope.client.lastName}    ${requestScope.client.email}</dd>

            <dt class="col-sm-3">${discount}</dt>
            <dd class="col-sm-9">${requestScope.discount} %</dd>

            <dt class="col-sm-3">${totalCost}</dt>
            <dd class="col-sm-9">${requestScope.totalCost} £</dd>

            <dt class="col-sm-3">${content}:</dt>
<%--            <dd class="col-sm-9">${sessionScope.userEmail}</dd>--%>

<%--            <dt class="col-sm-3"></dt>--%>
<%--            <dd class="col-sm-9">--%>
<%--                <dl class="row">--%>
<%--                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/controller?command=go_to_update_profile"--%>
<%--                                                      style="color: black; font-size: 18px">${updateProfile}</a></dt>--%>
<%--                </dl>--%>
<%--                <dl class="row">--%>
<%--                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/jsp/update-password.jsp"--%>
<%--                                                      style="color: black; font-size: 18px">${updatePassword}</a></dt>--%>
<%--                </dl>--%>
<%--            </dd>--%>

<%--            <dt class="col-sm-3">---------------------</dt>--%>
<%--            <dd class="col-sm-9"><dl class="row"><dt class="col-sm-4">------------------------</dt></dl></dd>--%>

<%--            <dt class="col-sm-3">${goTo}</dt>--%>
<%--            <dd class="col-sm-9">--%>
<%--                <dl class="row">--%>
<%--                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/controller?command=show_all_users"--%>
<%--                                                      style="color: black; font-size: 18px">${showAllUsers}</a></dt>--%>
<%--                </dl>--%>
<%--                <dl class="row">--%>
<%--                    <dt class="col-sm-4">&#9679;   <a href="${pageContext.request.contextPath}/controller?command=show_all_orders"--%>
<%--                                                      style="color: black; font-size: 18px">${showAllOrders}</a></dt>--%>
<%--                </dl>--%>
<%--            </dd>--%>
        </dl>
    </div>
</div>

<div class="container" style="height: 70vh">
    <div class="row">
        <table class="table table-striped" style="height: inherit">
            <thead>
            <tr>
                <th scope="col">${image}</th>
                <th scope="col">${type}</th>
                <th scope="col">${description}</th>
                <th scope="col">${manufacturer}</th>
                <th scope="col">${quantity}</th>
                <th scope="col">${unitPrice}</th>
<%--                <th scope="col">${status}</th>--%>
<%--                <th scope="col">${verified}</th>--%>
<%--                <th scope="col">${deleteRestore}</th>--%>
<%--                <th scope="col"></th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${requestScope.itemsList}">
                <tr>
                    <td><img width="70" src="${pageContext.request.contextPath}/uploadImage?imagePath=${element.key.imageUrl}"
                             alt=""></td>
<%--                    <td>${element.key.imageUrl}</td>--%>
                    <td>${element.key.type}</td>
                    <td>${element.key.description}</td>
                    <td>${element.key.manufacturer}</td>
                    <td>${element.value}</td>
                    <td>${element.key.price}</td>
<%--                    <td>${element.userStatus}</td>--%>
<%--                    <td>${element.verified}</td>--%>
<%--                    <td>--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${element.userId == sessionScope.userId}">-</c:when>--%>
<%--                            <c:when test="${element.userStatus == 'ACTIVE'}">--%>
<%--                                <a href="${pageContext.request.contextPath}/controller?command=delete_user&userId=${element.userId}"--%>
<%--                                   style="color: crimson">--%>
<%--                                        ${delete}--%>
<%--                                </a>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                <a href="${pageContext.request.contextPath}/controller?command=restore_user&userId=${element.userId}"--%>
<%--                                   style="color: darkslateblue">--%>
<%--                                        ${restore}--%>
<%--                                </a>--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <a href="${pageContext.request.contextPath}/controller?command=open_profile&userId=${element.userId}&userRole=${element.role}"--%>
<%--                           style="color: darkgreen">--%>
<%--                                ${openProfile}--%>
<%--                        </a>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
