<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="allUsers.id" var="id"/>
<fmt:message key="allUsers.firstName" var="name"/>
<fmt:message key="allUsers.lastName" var="surname"/>
<fmt:message key="allUsers.login" var="login"/>
<fmt:message key="allUsers.email" var="email"/>
<fmt:message key="allUsers.isVerified" var="verified"/>
<fmt:message key="allUsers.role" var="role"/>
<fmt:message key="allUsers.status" var="status"/>
<fmt:message key="allUsers.balance" var="balance"/>
<fmt:message key="allUsers.discount" var="discount"/>
<fmt:message key="allUsers.chooseDiscount" var="choose"/>
<fmt:message key="allUsers.updateDiscount" var="update"/>
<fmt:message key="allUsers.userProfile" var="title"/>


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
<br/>

<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${requestScope.client != null}">
                <dl class="row">
                    <dt class="col-sm-3">${id}</dt>
                    <dd class="col-sm-9">${requestScope.client.userId}</dd>

                    <dt class="col-sm-3">${login}</dt>
                    <dd class="col-sm-9">${requestScope.client.login}</dd>

                    <dt class="col-sm-3">${name} ${surname}</dt>
                    <dd class="col-sm-9">${requestScope.client.firstName} ${requestScope.client.lastName}</dd>

                    <dt class="col-sm-3">${email}</dt>
                    <dd class="col-sm-9">${requestScope.client.email}</dd>

                    <dt class="col-sm-3">${status}</dt>
                    <dd class="col-sm-9">${requestScope.client.userStatus}</dd>

                    <dt class="col-sm-3">${role}</dt>
                    <dd class="col-sm-9">${requestScope.client.role}</dd>

                    <dt class="col-sm-3">${balance}</dt>
                    <dd class="col-sm-9">${requestScope.client.money}</dd>

                    <dt class="col-sm-3">${discount}</dt>
                    <dd class="col-sm-9">
                        <p>${requestScope.discount} %</p>
                        <p>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change_client_discount">
                        <input type="hidden" name="clientId" value="${requestScope.client.userId}">
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="inputState">${choose}</label>
                                    <select id="inputState" class="form-control" name="newDiscountId">
                                        <c:forEach items="${requestScope.discountList}" var="discountEl">
                                            <option value="${discountEl.discountId}">${discountEl.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="form-group col-md-3">
                                <button type="submit" class="btn btn-primary" style="background-color: forestgreen">${update}</button>
                            </div>
                        </form>
                        </p>
                    </dd>

                    <dt class="col-sm-3">Term</dt>
                    <dd class="col-sm-9">
                        <p>Definition for the term.</p>
                        <p>And some more placeholder definition text.</p>
                    </dd>

                    <dt class="col-sm-3">Another term</dt>
                    <dd class="col-sm-9">This definition is short, so no extra paragraphs or anything.</dd>

                    <dt class="col-sm-3 text-truncate">Truncated term is truncated</dt>
                    <dd class="col-sm-9">This can be useful when space is tight. Adds an ellipsis at the end.</dd>

                    <dt class="col-sm-3">Nesting</dt>
                    <dd class="col-sm-9">
                        <dl class="row">
                            <dt class="col-sm-4">Nested definition list</dt>
                            <dd class="col-sm-8">I heard you like definition lists. Let me put a definition list inside
                                your definition list.
                            </dd>
                        </dl>
                    </dd>
                </dl>
            </c:when>
        </c:choose>
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
