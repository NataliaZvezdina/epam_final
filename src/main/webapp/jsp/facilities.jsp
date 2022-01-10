<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="facilities.title" var="title"/>
<fmt:message key="facilities.name" var="name"/>
<fmt:message key="facilities.description" var="description"/>
<fmt:message key="facilities.price" var="price"/>
<fmt:message key="facilities.appointment" var="appoint"/>
<fmt:message key="facilities.delete" var="delete"/>
<fmt:message key="facilities.restore" var="restore"/>
<fmt:message key="facilities.edit" var="edit"/>
<fmt:message key="facilities.add" var="newOne"/>
<fmt:message key="facilities.makeAppointment" var="appointment"/>
<fmt:message key="facilities.notAccessible" var="notAccess"/>

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
<div class="sign" style="height: 67vh">
    <c:import url="fragment/header.jsp"/>

    <table class="table table-striped" style="height: inherit">
        <thead>
        <tr>
            <th scope="col">${name}</th>
            <th scope="col">${description}</th>
            <th scope="col">${price}</th>
            <c:if test="${sessionScope.userRole=='ADMIN'}">
                <th scope="col">${delete} / ${restore}</th>
                <th scope="col">${edit}</th>
            </c:if>
            <c:if test="${sessionScope.userRole=='CLIENT'}">
                <th scope="col">${appoint}</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="element" items="${requestScope.facilitiesList}">
            <tr>
                <td>${element.name}</td>
                <td>${element.description}</td>
                <c:choose>
                    <c:when test="${element.accessible}">
                        <td>${element.price}</td>
                    </c:when>
                    <c:when test="${!element.accessible}">
                        <td>-</td>
                    </c:when>
                </c:choose>
                <c:if test="${sessionScope.userRole=='ADMIN'}">
                    <td>
                        <c:choose>
                            <c:when test="${element.accessible}">
                                <a href="${pageContext.request.contextPath}/controller?command=delete_facility&facilityId=${element.facilityId}&page=${requestScope.page}"
                                   style="color: crimson">
                                        ${delete}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/controller?command=restore_facility&facilityId=${element.facilityId}&page=${requestScope.page}"
                                   style="color: darkslateblue">
                                        ${restore}</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_facility&facilityId=${element.facilityId}&page=${requestScope.page}">
                            &#9998;
                        </a>
                    </td>
                </c:if>
                <c:if test="${sessionScope.userRole=='CLIENT'}">
                    <c:choose>
                        <c:when test="${element.accessible}">
                            <td>
                                <a style="color: midnightblue; font-weight: bold; font-size: 20px"
                                   href="${pageContext.request.contextPath}/controller?command=make_appointment&facilityId=${element.facilityId}&page=${requestScope.page}">
                                        ${appointment}</a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>${notAccess}</td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="container">
        <div class="row" style="justify-content: center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item">
                        <c:choose>
                            <c:when test="${requestScope.page > 1}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/controller?command=show_all_facilities&page=${requestScope.page-1}"
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
                           href="${pageContext.request.contextPath}/controller?command=show_all_facilities&page=${requestScope.page+1}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <br/>
    <c:if test="${sessionScope.userRole=='ADMIN'}">
        <h3 align="center">
            <a href="${pageContext.request.contextPath}/jsp/admin/add-facility.jsp"
               style="color: darkgreen">${newOne}</a>
        </h3>
    </c:if>

    <c:import url="fragment/footer.jsp"/>
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
</body>
</html>
