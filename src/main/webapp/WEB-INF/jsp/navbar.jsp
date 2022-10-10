<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Login</title>
</head>

<body>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/login">
        <div>
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
<div>
    <a href="register" />
    Register
</div>
<div>
    <a href="feedback" />
    Give us a feedback
</div>
</body>

</html>
