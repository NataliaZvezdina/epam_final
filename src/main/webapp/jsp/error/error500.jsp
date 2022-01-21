<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="error500.page" var="title"/>
<fmt:message key="error500.request" var="request"/>
<fmt:message key="error500.servlet" var="servlet"/>
<fmt:message key="error500.statusCode" var="code"/>
<fmt:message key="error500.exception" var="exception"/>
<fmt:message key="error500.message" var="message"/>
<fmt:message key="error500.stackTrace" var="stackTrace"/>
<fmt:message key="error500.backHome" var="home"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <title>${title}</title>
</head>
<body>

${request} ${pageContext.errorData.requestURI} is failed <br/>
<hr/>
${servlet} ${pageContext.errorData.servletName} <br/>
<hr/>
${code} ${pageContext.errorData.statusCode} <br/>
<hr/>
${exception} ${requestScope.exception} <br/>
<hr/>
${message} ${requestScope.exception.message} <br/>
<hr/>
${stackTrace} <br/>
<c:forEach var="stackTraceElement" items="${requestScope.exception.stackTrace}">
    <c:out value="${stackTraceElement}"/><br/>
</c:forEach><br/>
<hr/>

<a style="height: 25px; color: midnightblue" href="${pageContext.request.contextPath}/index.jsp">${home}</a>

<script>
    $(function () {
        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    });
</script>
</body>
</html>
