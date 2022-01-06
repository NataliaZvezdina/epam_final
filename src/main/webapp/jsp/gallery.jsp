<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 25/12/2021
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.gallery" var="gallery"/>
<fmt:message key="gallery.message" var="message"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.css"/>
    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <title>${gallery}</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>

<section class="gallery">
    <div class="container">
        <h2>${message}</h2>
        <div class="gallery-wrapper">
            <div class="card" data-fancybox
                 data-src="https://static-sl.insales.ru/files/1/2901/15125333/original/%D0%BC%D0%B5%D0%B4%D1%83%D0%B7%D0%B0_%D0%B8_%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg">
                <img src="https://static-sl.insales.ru/files/1/2901/15125333/original/%D0%BC%D0%B5%D0%B4%D1%83%D0%B7%D0%B0_%D0%B8_%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg"
                     alt=""></div>
            <div class="card" data-fancybox data-src="https://haycaptaintattoo.com/images/soveti/pirsing-popular-2.jpg">
                <img src="https://haycaptaintattoo.com/images/soveti/pirsing-popular-2.jpg" alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://icdn.lenta.ru/images/2021/07/10/06/20210710064944607/square_1280_webp_bd082dddd7ba9e69546d7e0a962dc56d.webp">
                <img src="https://icdn.lenta.ru/images/2021/07/10/06/20210710064944607/square_1280_webp_bd082dddd7ba9e69546d7e0a962dc56d.webp"
                     alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://irecommend.ru/sites/default/files/product-images/5009/nHkZspK6hoN2jmQTMQRx5g.jpeg">
                <img src="https://irecommend.ru/sites/default/files/product-images/5009/nHkZspK6hoN2jmQTMQRx5g.jpeg"
                     alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://static-sl.insales.ru/files/1/2901/15125333/original/%D0%BC%D0%B5%D0%B4%D1%83%D0%B7%D0%B0_%D0%B8_%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg">
                <img src="https://static-sl.insales.ru/files/1/2901/15125333/original/%D0%BC%D0%B5%D0%B4%D1%83%D0%B7%D0%B0_%D0%B8_%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg"
                     alt=""></div>
            <div class="card" data-fancybox data-src="https://haycaptaintattoo.com/images/soveti/pirsing-popular-2.jpg">
                <img src="https://haycaptaintattoo.com/images/soveti/pirsing-popular-2.jpg" alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://icdn.lenta.ru/images/2021/07/10/06/20210710064944607/square_1280_webp_bd082dddd7ba9e69546d7e0a962dc56d.webp">
                <img src="https://icdn.lenta.ru/images/2021/07/10/06/20210710064944607/square_1280_webp_bd082dddd7ba9e69546d7e0a962dc56d.webp"
                     alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://irecommend.ru/sites/default/files/product-images/5009/nHkZspK6hoN2jmQTMQRx5g.jpeg">
                <img src="https://irecommend.ru/sites/default/files/product-images/5009/nHkZspK6hoN2jmQTMQRx5g.jpeg"
                     alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://static-sl.insales.ru/files/1/2901/15125333/original/%D0%BC%D0%B5%D0%B4%D1%83%D0%B7%D0%B0_%D0%B8_%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg">
                <img src="https://static-sl.insales.ru/files/1/2901/15125333/original/%D0%BC%D0%B5%D0%B4%D1%83%D0%B7%D0%B0_%D0%B8_%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg"
                     alt=""></div>
            <div class="card" data-fancybox data-src="https://haycaptaintattoo.com/images/soveti/pirsing-popular-2.jpg">
                <img src="https://haycaptaintattoo.com/images/soveti/pirsing-popular-2.jpg" alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://icdn.lenta.ru/images/2021/07/10/06/20210710064944607/square_1280_webp_bd082dddd7ba9e69546d7e0a962dc56d.webp">
                <img src="https://icdn.lenta.ru/images/2021/07/10/06/20210710064944607/square_1280_webp_bd082dddd7ba9e69546d7e0a962dc56d.webp"
                     alt=""></div>
            <div class="card" data-fancybox
                 data-src="https://irecommend.ru/sites/default/files/product-images/5009/nHkZspK6hoN2jmQTMQRx5g.jpeg">
                <img src="https://irecommend.ru/sites/default/files/product-images/5009/nHkZspK6hoN2jmQTMQRx5g.jpeg"
                     alt=""></div>
        </div>
    </div>
</section>

<c:import url="fragment/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@4.0/dist/fancybox.umd.js"></script>
</body>
</html>
