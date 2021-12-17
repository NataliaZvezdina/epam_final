<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 16/12/2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Login page</title>
</head>
<body>
${message}
<br/>

<form action="../controller?command=login_user" method="post">
    Input login<br/>
    <input type="text" name="login" required="required"/><br/>
    Input password<br/>
    <input type="password" name="password" required="required"/><br/>
    <input type="submit" value="submit">
</form>

</body>
</html>
