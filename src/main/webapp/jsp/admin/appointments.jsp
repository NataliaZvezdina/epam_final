<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="allAppointments.page" var="title"/>
<fmt:message key="allAppointments.id" var="id"/>
<fmt:message key="relevantAppointments.empty" var="emptyList"/>
<fmt:message key="relevantAppointments.date" var="date"/>
<fmt:message key="relevantAppointments.time" var="time"/>
<fmt:message key="relevantAppointments.facility" var="facility"/>
<fmt:message key="relevant.details" var="details"/>

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
<div class="sign" style="height: 100vh">
    <c:import url="../fragment/header.jsp"/>

    <%--<c:choose>--%>
    <%--    <c:when test="empty ${requestScope.appointmentData}">--%>
    <%--        <div class="container" style="height: 69vh">--%>
    <%--            <br/><br/><h4 style="color: black">${emptyList}</h4>--%>
    <%--        </div>--%>
    <%--    </c:when>--%>
    <%--    <c:otherwise>--%>

    <div class="container" style="height: 90vh">
        <div class="row">
            <table class="table table-striped" style="height: inherit">
                <thead>
                <tr>
                    <th scope="col">${id}</th>
                    <th scope="col">${date}</th>
                    <th scope="col">${time}</th>
                    <th scope="col">${facility}</th>
                    <th scope="col"></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="element" items="${requestScope.appointmentData}">
                    <tr>
                        <td>${element.key.appointmentId}</td>
                        <td>${element.key.dateTime.toLocalDate()}</td>
                        <td>${element.key.dateTime.toLocalTime()}</td>
                        <td>${element.value.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=open_single_appointment&appointmentId=${element.key.appointmentId}"
                               style="color: darkgreen">
                                    ${details}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="container">
        <div class="row" style="justify-content: center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item">
                        <c:choose>
                            <c:when test="${requestScope.page > 1}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/controller?command=show_all_appointments_by_admin&page=${requestScope.page-1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="page-link"
                                   href="#"
                                   aria-label="Previous" hidden>
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li class="page-item"><span class="page-link">${requestScope.page}</span></li>
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/controller?command=show_all_appointments_by_admin&page=${requestScope.page+1}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <%--    </c:otherwise>--%>
    <%--</c:choose>--%>


    <c:import url="../fragment/footer.jsp"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
</body>
</html>
