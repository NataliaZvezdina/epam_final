<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="makeAppointment.page" var="title"/>
<fmt:message key="makeAppointment.chosen" var="chosen"/>
<fmt:message key="makeAppointment.button" var="button"/>
<fmt:message key="makeAppointment.day" var="date"/>
<fmt:message key="makeAppointment.hour" var="hour"/>
<fmt:message key="makeAppointment.piercer" var="pierce"/>
<fmt:message key="makeAppointment.notes" var="notices"/>


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
        <input type="hidden" name="command" value="make_appointment">
        <input type="hidden" name="facilityId" value="${requestScope.facilityId}">
        <div class="form-group">
            <label for="exampleFormControlInput1">${chosen}</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="facilityName"
                   value="${requestScope.facilityName}" readonly>
        </div>

        <div class="form-group">
            <label for="exampleFormControlSelect1">${pierce}</label>${requestScope.piercer}
            <select class="form-control" id="exampleFormControlSelect1" name="piercerId" required>
                <c:forEach var="piercer" items="${requestScope.piercersList}">
                    <option value="${piercer.userId}">${piercer.firstName} ${piercer.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput2">${date}</label>
            <jsp:useBean id="now" class="java.util.Date"/>
            <input type="date" min="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" required
                   class="form-control" id="exampleFormControlInput2" name="date"
                   value="${requestScope.formData['date']}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect2">${hour}</label>
            <select class="form-control" id="exampleFormControlSelect2" required name="hour">
                <option value="10">10.00</option>
                <option value="11">11.00</option>
                <option value="12">12.00</option>
                <option value="13">13.00</option>
                <option value="14">14.00</option>
                <option value="15">15.00</option>
                <option value="16">16.00</option>
                <option value="17">17.00</option>
                <option value="18">18.00</option>
                <option value="19">19.00</option>
                <option value="20">20.00</option>
            </select>
        </div>

        <div class="form-group">
            <label for="exampleFormControlTextarea1">${notices}</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="notes">
                ${requestScope.formData['notes']}
            </textarea>
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
                    style="background-color: forestgreen; border-color: forestgreen; justify-content: center">${button}</button>
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
