<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.home" var="home"/>
<fmt:message key="navigation.jewelry" var="jewelry"/>
<fmt:message key="navigation.facilities" var="facilities"/>
<fmt:message key="navigation.piercers" var="piercers"/>
<fmt:message key="navigation.gallery" var="gallery"/>
<fmt:message key="navigation.contacts" var="contacts"/>
<fmt:message key="navigation.about" var="about"/>
<fmt:message key="navigation.logOut" var="logOut"/>
<fmt:message key="navigation.signIn" var="signIn"/>
<fmt:message key="navigation.signUp" var="signUp"/>
<fmt:message key="navigation.language" var="language"/>
<fmt:message key="navigation.profile" var="profile"/>
<fmt:message key="client.header.balance" var="balance"/>
<fmt:message key="client.topUpBalance" var="topUpBalance"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <img width="120" src="https://icon-library.com/images/piercing-icon/piercing-icon-2.jpg" alt="">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/guest/home.jsp">${home}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/controller?command=show_all_jewelry">${jewelry}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/controller?command=show_all_facilities">${facilities}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/controller?command=show_all_active_piercers">${piercers}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/jsp/content/gallery.jsp">${gallery}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/content/contacts.jsp">${contacts}</a>
                </li>
            </ul>

            <ul>
                <c:if test="${sessionScope.authorization}">
                    <li class="nav-item">
                        ${sessionScope.userName} ${sessionScope.userLastName} ${sessionScope.userRole}
                    </li>
                    <c:if test="${sessionScope.userRole == 'CLIENT'}">
                        <li class="nav-item">
                            ${balance}: ${sessionScope.userMoney} £ <a style="height: 30px"
                                    href="${pageContext.request.contextPath}/controller?command=show_basket">&#128722</a>

                        </li>
                        <c:if test="${sessionScope.userDiscount != 0}">
                            <li class="nav-item">
                                <p>Discount: ${sessionScope.userDiscount} %</p>
                            </li>
                        </c:if>
                    </c:if>
                    <li class="nav-item" style="lighting-color: darkgrey"><hr/></li>
                    <c:choose>
                        <c:when test="${sessionScope.userRole=='ADMIN'}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/jsp/admin/admin-profile.jsp">${profile}</a>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.userRole=='CLIENT'}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/jsp/client/top-up-balance.jsp">${topUpBalance}</a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/jsp/client/client-profile.jsp">${profile}</a>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.userRole=='PIERCER'}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/jsp/piercer/piercer-profile.jsp">${profile}</a>
                            </li>
                        </c:when>
                    </c:choose>

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
                            <a class="nav-link" href="${pageContext.request.contextPath}/jsp/guest/sign-in.jsp">${signIn}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/jsp/guest/sign-up.jsp">${signUp}</a>
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

