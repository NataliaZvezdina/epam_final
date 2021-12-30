<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.homePage" var="homePage"/>
<%--<fmt:message key="navigation.home" var="home"/>--%>
<%--<fmt:message key="navigation.jewelry" var="jewelry"/>--%>
<%--<fmt:message key="navigation.facilities" var="facilities"/>--%>
<%--<fmt:message key="navigation.contacts" var="contacts"/>--%>
<%--<fmt:message key="navigation.about" var="about"/>--%>
<%--<fmt:message key="navigation.logOut" var="logOut"/>--%>
<%--<fmt:message key="navigation.signIn" var="signIn"/>--%>
<%--<fmt:message key="navigation.signUp" var="signUp"/>--%>
<%--<fmt:message key="navigation.language" var="language"/>--%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <title>${homePage}</title>
</head>
<body>

<c:import url="header.jsp"/>

<%--<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">--%>
<%--    <ol class="carousel-indicators">--%>
<%--        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>--%>
<%--        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>--%>
<%--        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>--%>
<%--    </ol>--%>
<%--    <div class="carousel-inner">--%>
<%--        <div class="carousel-item active">--%>
<%--            <img src="http://lavzjewellery.ru/upload/iblock/32e/Cover.jpg" class="d-block w-100" alt="...">--%>
<%--            <div class="carousel-caption d-none d-md-block">--%>
<%--                <h5>First slide label</h5>--%>
<%--                <p>Some representative placeholder content for the first slide.</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="carousel-item">--%>
<%--            <img src="http://lavzjewellery.ru/upload/iblock/32e/Cover.jpg" class="d-block w-100" alt="...">--%>
<%--            <div class="carousel-caption d-none d-md-block">--%>
<%--                <h5>Second slide label</h5>--%>
<%--                <p>Some representative placeholder content for the second slide.</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="carousel-item">--%>
<%--            <img src="http://lavzjewellery.ru/upload/iblock/32e/Cover.jpg" class="d-block w-100" alt="...">--%>
<%--            <div class="carousel-caption d-none d-md-block">--%>
<%--                <h5>Third slide label</h5>--%>
<%--                <p>Some representative placeholder content for the third slide.</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">--%>
<%--        <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--        <span class="sr-only">Previous</span>--%>
<%--    </button>--%>
<%--    <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">--%>
<%--        <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--        <span class="sr-only">Next</span>--%>
<%--    </button>--%>
<%--</div>--%>

<div class="container">
    <div class="row" style="justify-content: space-between">
        <div class="card" style="width: 15rem;">
            <img src="https://i.ebayimg.com/thumbs/images/g/bI4AAOSwq4VejxQO/s-l300.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://images.squarespace-cdn.com/content/v1/5546cbc9e4b08d5dd934fccc/1526100470195-TABC88QOJM1BUJLGPYVG/EarPiercingDiagram-Website.png" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://cdn.shopify.com/s/files/1/0131/2368/2363/articles/Pierce_of_Art_Price_List_large_93837fcd-2cd1-4452-9f38-15f8f646c5d2_410x.jpg?v=1546814831" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://ae01.alicdn.com/kf/H3d6c5c1961c148ba805760a8f80ea7fag/1PC-Disposable-Sterile-Ear-Piercing-Unit-For-Baby-Ear-Tragus-Piercing-Gun-Little-Children-NO-PAIN.jpg_Q90.jpg_.webp" class="card-img-top" alt="...">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
    </div>
</div>

<c:import url="footer.jsp"/>

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

