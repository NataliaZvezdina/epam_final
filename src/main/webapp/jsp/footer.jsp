<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 24/12/2021
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <%--        <a class="navbar-brand" href="#">Navbar</a>--%>
        <img width="120" src="https://icon-library.com/images/piercing-icon/piercing-icon-2.jpg" alt="">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContentf"
                aria-controls="navbarSupportedContentf" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContentf">
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
                    <a class="nav-link active" aria-current="page" href="#">${contacts}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">${about}</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
