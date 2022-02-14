<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="navigation.homePage" var="homePage"/>

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

<c:import url="../fragment/header.jsp"/>
<div class="container" style="height: 67vh">
    <br/>
    <div class="row" style="justify-content: space-between">
        <div class="card" style="width: 15rem;">
            <img src="https://media.istockphoto.com/vectors/tattoo-and-piercing-icons-vector-id481563106?k=20&m=481563106&s=612x612&w=0&h=W0Uwmjl-8Hdlz9975ACnWv_hkZszrw9VczVTmapxPE4=" class="card-img-top" alt="...">
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD_fzkIn9_o8q6jNFJoUpFBtpVjJJ0WAVmmXmAU282ebI-jPosaS7EjRY7lpdVb0Lj7us&usqp=CAU" class="card-img-top" alt="...">
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://m.media-amazon.com/images/I/71gGBOu6wsS._AC_UL320_.jpg" class="card-img-top" alt="...">
        </div>
        <div class="card" style="width: 15rem;">
            <img src="https://media.istockphoto.com/vectors/beauty-and-fashion-medical-vector-illustration-diagram-with-types-of-vector-id980179190?k=20&m=980179190&s=612x612&w=0&h=RrxzqJcAorvj9xWFJjKreo6-i_6_bUJ9Xd4M1FE1Kfw=" class="card-img-top" alt="...">
        </div>
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

