<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="jewelry.image" var="image"/>
<fmt:message key="jewelry.type" var="type"/>
<fmt:message key="jewelry.description" var="description"/>
<fmt:message key="jewelry.manufacturer" var="manufacturer"/>
<fmt:message key="basket.quantity" var="quantity"/>
<fmt:message key="basket.pricePerUnit" var="unitPrice"/>
<fmt:message key="basket.addItem" var="addItem"/>
<fmt:message key="basket.removeItem" var="removeItem"/>
<fmt:message key="basket.empty" var="emptyBasket"/>
<fmt:message key="basket.totalCost" var="totalCost"/>
<fmt:message key="basket.makeOrder" var="makeOrder"/>
<fmt:message key="basket.page" var="title"/>

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
<c:import url="header.jsp"/>

<div class="container" style="height: 100vh">
    <div class="row">
        </br></br>
        <c:choose>
            <c:when test="${empty sessionScope.basket}">
                <div class="row" style="justify-content: center">
                    <h3 align="center">${emptyBasket}</h3>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-striped" style="height: inherit">
                    <thead>
                    <tr>
                        <th scope="col">${image}</th>
                        <th scope="col">${type}</th>
                        <th scope="col">${description}</th>
                        <th scope="col">${manufacturer}</th>
                        <th scope="col">${unitPrice}</th>
                        <th scope="col">${quantity}</th>
                        <th scope="col">${addItem}</th>
                        <th scope="col">${removeItem}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="element" items="${requestScope.basketItemsList}">
                        <tr>
                            <td><img width="100"
                                     src="${pageContext.request.contextPath}/uploadImage?imagePath=${element.imageUrl}"
                                     alt=""></td>
                            <td>${element.type}</td>
                            <td>${element.description}</td>
                            <td>${element.manufacturer}</td>
                            <td>${element.price}</td>
                            <td>${sessionScope.basket.get(element.jewelryId)}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=recount_order_while_adding_item&jewelryId=${element.jewelryId}">&#10010;</a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=recount_order_while_removing_item&jewelryId=${element.jewelryId}">&#8212;</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        </hr></br>

        <c:if test="${sessionScope.basket != null && !empty sessionScope.basket}">
            <p align="center">${totalCost}: ${requestScope.totalCost} £</p>
            <div class="container">
                <div class="row" style="justify-content: center">
                    <a class="btn btn-primary" style="justify-content: center"
                       href="${pageContext.request.contextPath}/controller?command=create_order"
                       role="button">${makeOrder}</a>
                </div>
            </div>
        </c:if>

        </br></br>
    </div>
</div>

<c:import url="footer.jsp"/>
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
