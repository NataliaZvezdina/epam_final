<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="admin.delete.restore.user" var="deleteRestore"/>
<fmt:message key="admin.delete.user" var="delete"/>
<fmt:message key="admin.restore.user" var="restore"/>
<fmt:message key="allUsers.id" var="id"/>
<fmt:message key="allUsers.firstName" var="name"/>
<fmt:message key="allUsers.lastName" var="surname"/>
<fmt:message key="allUsers.login" var="login"/>
<fmt:message key="allUsers.email" var="email"/>
<fmt:message key="allUsers.isVerified" var="verified"/>
<fmt:message key="allUsers.role" var="role"/>
<fmt:message key="allUsers.status" var="status"/>
<fmt:message key="allUsers.openProfile" var="openProfile"/>
<fmt:message key="allUsers.usersList" var="title"/>

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

<div class="container" style="height: 68vh">
    <div class="row">
        <table class="table table-striped" style="height: inherit">
            <thead>
            <tr>
                <th scope="col">${id}</th>
                <th scope="col">${login}</th>
                <th scope="col">${name}</th>
                <th scope="col">${surname}</th>
                <th scope="col">${email}</th>
                <th scope="col">${role}</th>
                <th scope="col">${status}</th>
                <th scope="col">${verified}</th>
                <th scope="col">${deleteRestore}</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${requestScope.usersList}">
                <tr>
                    <td>${element.userId}</td>
                    <td>${element.login}</td>
                    <td>${element.firstName}</td>
                    <td>${element.lastName}</td>
                    <td>${element.email}</td>
                    <td>${element.role}</td>
                    <td>${element.userStatus}</td>
                    <td>${element.verified}</td>
                    <td>
                        <c:choose>
                            <c:when test="${element.userId == sessionScope.userId}">-</c:when>
                            <c:when test="${element.userStatus == 'ACTIVE'}">
                                <a href="${pageContext.request.contextPath}/controller?command=delete_user&userId=${element.userId}"
                                   style="color: crimson">
                                        ${delete}
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/controller?command=restore_user&userId=${element.userId}"
                                   style="color: darkslateblue">
                                        ${restore}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=open_profile&userId=${element.userId}&userRole=${element.role}"
                           style="color: darkgreen">
                                ${openProfile}
                        </a>
                    </td>
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
