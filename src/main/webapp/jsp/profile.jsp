<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 17/12/2021
  Time: 02:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>
<fmt:message key="profile.backHome" var="backHome"/>

<!doctype html>
<html lang="en">
<head>
    <title>Profile page</title>
</head>
<body>
Hi ${sessionScope.user_name} !!

<a href="${pageContext.request.contextPath}/index.jsp">${backHome}</a>
</body>
</html>
