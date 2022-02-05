<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="editJewelry.title" var="title"/>
<fmt:message key="jewelry.image" var="image"/>
<fmt:message key="jewelry.type" var="type"/>
<fmt:message key="jewelry.description" var="description"/>
<fmt:message key="jewelry.manufacturer" var="manufacturer"/>
<fmt:message key="jewelry.price" var="price"/>
<fmt:message key="editJewelry.id" var="id"/>
<fmt:message key="editJewelry" var="update"/>

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
        <input type="hidden" name="command" value="edit_jewelry">
        <input type="hidden" name="isAvailable" value="${requestScope.jewelryToEdit.available}">
        <input type="hidden" name="page" value="${requestScope.page}">
        <div class="form-group">
            <label for="readOnlyInput">${id}</label>
            <input class="form-control" type="number" id="readOnlyInput" name="jewelryId"
                   value="${requestScope.jewelryToEdit.jewelryId}" readonly>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1">${image}</label>
            <input type="text" class="form-control" id="exampleFormControlInput1"
                   required pattern="([^\s]+(\.(?i)(jpe?g|png|gif|bmp))$)" name="imageUrl"
                   value="${requestScope.jewelryToEdit.imageUrl}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput2">${type}</label>
            <input type="text" class="form-control" id="exampleFormControlInput2" required name="type"
                   value="${requestScope.jewelryToEdit.type}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput3">${manufacturer}</label>
            <input type="text" class="form-control" id="exampleFormControlInput3" name="manufacturer"
                   value="${requestScope.jewelryToEdit.manufacturer}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput4">${price}</label>
            <input type="number" step="0.01" min="0" max="999.99" pattern="^[0-9]{1,3}(\.[0-9]{1,2})?$"
                   class="form-control" id="exampleFormControlInput4" name="price" required
                   value="${requestScope.jewelryToEdit.price}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">${description}</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" required name="description">
                ${requestScope.jewelryToEdit.description}</textarea>
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
                    style="background-color: forestgreen; border-color: forestgreen; justify-content: center">${update}</button>
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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
</body>
</html>
