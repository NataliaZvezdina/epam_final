<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 24/12/2021
  Time: 00:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.home" var="home"/>
<fmt:message key="navigation.jewelry" var="jewelry"/>
<fmt:message key="navigation.facilities" var="facilities"/>
<fmt:message key="navigation.contacts" var="contacts"/>
<fmt:message key="navigation.about" var="about"/>
<fmt:message key="navigation.logOut" var="logOut"/>
<fmt:message key="navigation.signIn" var="signIn"/>
<fmt:message key="navigation.signUp" var="signUp"/>
<fmt:message key="navigation.language" var="language"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../static/home-panels.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <%--        <a class="navbar-brand" href="#">Navbar</a>--%>
        <img width="120" src="https://icon-library.com/images/piercing-icon/piercing-icon-2.jpg" alt="">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/home.jsp">${home}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">${jewelry}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/jsp/facilities.jsp">${facilities}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">${contacts}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">${about}</a>
                </li>

            </ul>

            <ul>
                <c:choose>
                    <c:when test="${sessionScope.authorization}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=logout">${logOut}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/jsp/sign-in.jsp">${signIn}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">${signUp}</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <li class="nav-item dropdown"><a
                        class="nav-link dropdown-toggle" href="#"
                        id="navbarDropdownMenuLink" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"> ${language} </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=change_locale&amp;locale=en">EN
                            (English)</a>

                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/controller?command=change_locale&amp;locale=ru">RU
                            (Русский)
                        </a>
                    </div>
                </li>

            </ul>
        </div>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>
