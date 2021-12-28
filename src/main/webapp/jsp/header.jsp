<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.home" var="home"/>
<fmt:message key="navigation.jewelry" var="jewelry"/>
<fmt:message key="navigation.facilities" var="facilities"/>
<fmt:message key="navigation.gallery" var="gallery"/>
<fmt:message key="navigation.contacts" var="contacts"/>
<fmt:message key="navigation.about" var="about"/>
<fmt:message key="navigation.logOut" var="logOut"/>
<fmt:message key="navigation.signIn" var="signIn"/>
<fmt:message key="navigation.signUp" var="signUp"/>
<fmt:message key="navigation.language" var="language"/>
<fmt:message key="navigation.profile" var="profile"/>

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
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/jsp/gallery.jsp">${gallery}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/contacts.jsp">${contacts}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">${about}</a>
                </li>

            </ul>

            <ul>
                <c:if test="${sessionScope.authorization}">
                    <li class="nav-item">
                        <p>${sessionScope.userName} ${sessionScope.userLastName} ${sessionScope.userRole}</p>
                    </li>
                    <c:if test="${sessionScope.userRole == 'CLIENT'}">
                        <li class="nav-item">
                            <p>${sessionScope.userMoney} £</p>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${sessionScope.userRole=='ADMIN'}">
                                <a href="${pageContext.request.contextPath}/jsp/admin-profile.jsp">${profile}</a>
                            </c:when>
                            <c:when test="${sessionScope.userRole=='CLIENT'}">
                                <a href="${pageContext.request.contextPath}/jsp/client-profile.jsp">${profile}</a>
                            </c:when>
                        </c:choose>
                    </li>
                </c:if>
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
                            <a class="nav-link" href="${pageContext.request.contextPath}/jsp/sign-up.jsp">${signUp}</a>
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

