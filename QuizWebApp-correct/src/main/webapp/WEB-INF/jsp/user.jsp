<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>

<body>
<form method="post" action="user">
    <label>
        id:
        <input type="text" name="id">
    </label>
    <label>
        username:
        <input type="text" name="username123">
    </label>
    <label>
        password:
        <input type="text" name="password">
    </label>
    <button type="submit">submit</button>
</form>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>password</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>
