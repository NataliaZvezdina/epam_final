<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>
<fmt:message key="index.title" var="title"/>

<!doctype html>
<html lang="en">
<head>
    <title>${title}</title>
</head>
<body>

<c:redirect url="jsp/guest/home.jsp"/>

</body>
</html>