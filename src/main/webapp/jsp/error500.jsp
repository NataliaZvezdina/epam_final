<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
<head>
    <title>Error 500 page</title>
</head>
<body>

Request from: ${pageContext.errorData.requestURI} is failed <br/><hr/>
Servlet name: ${pageContext.errorData.servletName} <br/><hr/>
Status code: ${pageContext.errorData.statusCode} <br/><hr/>
Exception: ${pageContext.exception} <br/><hr/>
Message from exception: ${pageContext.exception.message} <br/><hr/>
Stack trace: <br/>
<c:forEach var="stackTraceElem" items="${pageContext.exception.stackTrace}">
    <c:out value="${stackTraceElem}"/><br/>
</c:forEach><br/><hr/>

<a href="index.jsp">Back to index</a>

</body>
</html>
