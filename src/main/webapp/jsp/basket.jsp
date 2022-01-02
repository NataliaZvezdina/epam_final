<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="jewelry.image" var="image"/>
<fmt:message key="jewelry.type" var="type"/>
<fmt:message key="jewelry.description" var="description"/>
<fmt:message key="jewelry.manufacturer" var="manufacturer"/>

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
    <title>Basket</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<c:import url="header.jsp"/>

</br></br>
<c:choose>
    <c:when test="${sessionScope.basket == null}">
        <h3 align="center">Basket is empty</h3>
    </c:when>
    <c:otherwise>
        <table class="table table-striped" style="height: inherit">
            <thead>
            <tr>
                <th scope="col">${image}</th>
                <th scope="col">${type}</th>
                <th scope="col">${description}</th>
                <th scope="col">${manufacturer}</th>
                <th scope="col">${price}</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${requestScope.jewelryList}">
                <tr>
                    <td><img width="100" src="${pageContext.request.contextPath}/uploadImage?imagePath=${element.imageUrl}"
                             alt=""></td>
                    <td>${element.type}</td>
                    <td>${element.description}</td>
                    <td>${element.manufacturer}</td>
                    <c:choose>
                        <c:when test="${element.available}">
                            <td>${element.price}</td>
                        </c:when>
                        <c:when test="${!element.available}">
                            <td>-</td>
                        </c:when>
                    </c:choose>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>


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
