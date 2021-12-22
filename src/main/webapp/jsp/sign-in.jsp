<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 16/12/2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>SignIn page</title>
</head>
<body>
${message}
<br/>

<%--<form action="../controller?command=login_user" method="post">--%>
<%--    Input login<br/>--%>
<%--    <input type="text" name="login" required="required"/><br/>--%>
<%--    Input password<br/>--%>
<%--    <input type="password" name="password" required="required"/><br/>--%>
<%--    <input type="submit" value="submit">--%>
<%--</form>--%>
<div class="container">
    <div class="row">
        <form class="row g-3 needs-validation" novalidate
              action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_in">
            <div class="col-md-4">
                <label for="validationCustom01" class="form-label">Login</label>
                <input type="text" name="login" class="form-control" id="validationCustom01" required pattern="[A-Za-z0-9]{3,20}">
                <div class="invalid-feedback">
                    Incorrect login :(
                </div>
            </div>
            <div class="col-md-4">
                <label for="validationCustom02" class="form-label">Password</label>
                <input type="text" name="password" class="form-control" id="validationCustom02" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,}">
                <div class="invalid-feedback">
                    Incorrect password :(
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Submit</button>
            </div>

        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
