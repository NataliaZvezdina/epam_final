<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="jewelry.page.title" var="title"/>
<fmt:message key="jewelry.image" var="image"/>
<fmt:message key="jewelry.type" var="type"/>
<fmt:message key="jewelry.description" var="description"/>
<fmt:message key="jewelry.manufacturer" var="manufacturer"/>
<fmt:message key="jewelry.price" var="price"/>
<fmt:message key="jewelry.delete" var="delete"/>
<fmt:message key="jewelry.deleted" var="deleted"/>

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
<div class="sign" style="height: 67vh">
    <c:import url="header.jsp"/>

    <table class="table table-striped" style="height: inherit">
        <thead>
        <tr>
            <th scope="col">${image}</th>
            <th scope="col">${type}</th>
            <th scope="col">${description}</th>
            <th scope="col">${manufacturer}</th>
            <th scope="col">${price}</th>
            <c:if test="${sessionScope.userRole=='ADMIN'}">
                <th scope="col">${delete}</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${requestScope.jewelryList}" varStatus="status">

            <tr>
                <td><img width="100" src="${pageContext.request.contextPath}/uploadImage?imagePath=${element.imageUrl}"
                         alt=""></td>
                <td>${element.type}</td>
                <td>${element.description}</td>
                <td>${element.manufacturer}</td>
                <c:choose>
                    <c:when test="${element.available}">
                        <td>${element.price}</td>
                    </c:when>
                    <c:when test="${!element.available}">
                        <td>out of stock</td>
                    </c:when>
                </c:choose>
                <c:if test="${sessionScope.userRole=='ADMIN'}">
                    <c:choose>
                        <c:when test="${element.available}">
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=delete_jewelry&jewelryId=${element.jewelryId}">${delete}</a>
                            </td>
                        </c:when>
                        <c:when test="${!element.available}">
                            <td><a href="#"></a>${deleted}</td>
                        </c:when>
                    </c:choose>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="container">
        <div class="row" style="justify-content: center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item">
                        <c:choose>
                            <c:when test="${requestScope.page > 1}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/controller?command=show_all_jewelry&page=${requestScope.page-1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:when>
                            <c:when test="${requestScope.page < 1}">
                                <a class="page-link"
                                   href="#"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="page-item"><span class="page-link">${requestScope.page}</span></li>
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/controller?command=show_all_jewelry&page=${requestScope.page+1}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <br/>
    <hr/>
    <br/>
    <h2>Add new jewelry to list: </h2>
    <c:if test="${sessionScope.userRole=='ADMIN'}">
        <div class="container">
            <div class="row">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="add_jewelry">
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="validationServer01">${login}</label>
                            <input type="text" name="login" class="form-control is-valid" id="validationServer01"
                                   required
                                   pattern="[A-Za-z0-9]{3,20}">

                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="validationServer02">${password}</label>
                            <input type="password" name="password" class="form-control is-valid" id="validationServer02"
                                   required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                            <small id="passwordHelpBlock" class="form-text text-muted">
                                Your password must be 8-20 characters long, contain letters and numbers, and must not
                                contain
                                spaces, special characters, or emoji.
                            </small>

                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="validationServer002">${confirm}</label>
                            <input type="password" name="repeatPassword" class="form-control is-valid"
                                   id="validationServer002"
                                   required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">

                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="validationServer03">${name}</label>
                            <input type="text" name="firstName" class="form-control is-valid" id="validationServer03"
                                   required
                                   pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">

                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="validationServer04">${surname}</label>
                            <input type="text" name="lastName" class="form-control is-valid" id="validationServer04"
                                   required
                                   pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">

                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="validationServer05">${email}</label>
                            <input type="email" name="email" class="form-control is-valid" id="validationServer05"
                                   required
                                   pattern="[_0-9a-z][-_.0-9a-z]*@[0-9a-z][-.0-9a-z]*[0-9a-z]\.[a-z]{2,}">

                        </div>
                    </div>

                    <div class="row">
                        <c:if test="${requestScope.errorMessage != null}">
                            <div class="form-group">
                                <div class="col-md-6 mb-3">
                                    <div class="err-message-from-server">
                                        <fmt:setBundle basename="bundle/locale" var="rb"/>
                                        <fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </div>

                    <button class="btn btn-primary" type="submit">${signUp}</button>
                </form>
            </div>
        </div>
    </c:if>

    <c:import url="footer.jsp"/>
</div>

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
