<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="add.jewelry.page" var="title"/>
<fmt:message key="add.jewelry.button" var="add"/>
<fmt:message key="add.jewelry.path" var="image"/>
<fmt:message key="add.jewelry.type" var="type"/>
<fmt:message key="add.description" var="description"/>
<fmt:message key="add.manufacturer" var="manufacturer"/>
<fmt:message key="add.price" var="price"/>
<fmt:message key="add.price.helper" var="helperPrice"/>

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

<div class="container">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="add_jewelry">
        <div class="form-group">
            <label for="exampleFormControlInput1">${image}</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="imageUrl"
            <c:if test="${requestScope.jewelryToAdd != null}">
                   value="${requestScope.jewelryToAdd['imageUrl']}"</c:if>
                   required pattern="([^\s]+(\.(?i)(jpe?g|png|gif|bmp))$)">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput2">${type}</label>
            <input type="text" class="form-control" id="exampleFormControlInput2" name="type"
            <c:if test="${requestScope.jewelryToAdd != null}">
                   value="${requestScope.jewelryToAdd['type']}"</c:if>>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput3">${manufacturer}</label>
            <input type="text" class="form-control" id="exampleFormControlInput3" name="manufacturer"
            <c:if test="${requestScope.jewelryToAdd != null}">
                   value="${requestScope.jewelryToAdd['manufacturer']}"</c:if>>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput4">${price}</label>
            <input type="number" step="0.01" class="form-control" id="exampleFormControlInput4" name="price"
            <c:if test="${requestScope.jewelryToAdd != null}">
                   value="${requestScope.jewelryToAdd['price']}"</c:if>
                   required pattern="^[0-9]{1,4}(\.[0-9]{1,2})?$">
            <small id="passwordHelpBlockF" class="form-text text-muted" style="color: black">
                ${helperPrice}
            </small>
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">${description}</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description">
                <c:if test="${requestScope.jewelryToAdd != null}">
                    ${requestScope.jewelryToAdd['description']}</c:if></textarea>
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
        <div class="form-group col-md-10" style="justify-content: center">
            <button type="submit" class="btn btn-primary"
                    style="background-color: forestgreen; border-color: forestgreen; justify-content: center">${add}</button>
        </div>
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
</body>
</body>
</html>
