<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>

<body>
<h1>Register</h1>
<div>
    <form method="post" action="register">
        <div>
            <label>First Name</label>
            <input type="text" name="firstName">
        </div>
        <div>
            <label>Last Name</label>
            <input type="text" name="lastName">
        </div>
        <div>
            <label>email</label>
            <input type="text" name="email">
        </div>
        <div>
            <label>password</label>
            <input type="password" name="password">
        </div>
        <div>
            <label>phone</label>
            <input type="number" name="phone">
        </div>
        <div>
            <label>address</label>
            <input type="text" name="address">
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>

</html>
