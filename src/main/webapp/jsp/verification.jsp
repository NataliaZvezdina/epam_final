
<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 23/12/2021
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="verification.page" var="title"/>
<fmt:message key="verification.message" var="message"/>
<fmt:message key="verification.approved" var="approved"/>
<fmt:message key="verification.notApproved" var="not_approved"/>

<!doctype html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>${title}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-5 offset-lg-1">
            <div class="main-title">
                <h2>${message}</h2>
            </div>
            <c:choose>
                <c:when test="${requestScope.verification}">
                    <p>${approved}</p>
                </c:when>
                <c:otherwise>
                    <p>${not_approved}</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
