<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <c:if test="${sessionScope.userRole=='CLIENT'}">
                    <th scope="col">Add to basket</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${requestScope.jewelryList}" varStatus="status">

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

                    <c:if test="${sessionScope.userRole=='CLIENT'}">
                        <c:choose>
                            <c:when test="${element.available}">
                                <%--                            <td>&#10010;</td>--%>
                                <td><a href="${pageContext.request.contextPath}/controller?command=add_item_to_basket&jewelryId=${element.jewelryId}&page=${requestScope.page}">&#10010;</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>Not available</td>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
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
