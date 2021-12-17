<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 16/12/2021
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="locale"/>
<fmt:message key="title.not_found" var="locale_not_found"/>
<html lang="en">
<head>
<%--    <title>Error 404 page</title>--%>
    <title>${locale_not_found}</title>
</head>
<body>
<%--<h1 align="center">404 Page not found</h1>--%>
<p>${locale_not_found}</p>
</body>
</html>
