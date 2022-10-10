<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Contact</title>
</head>

<body>
<h1>Contact Us</h1>
<div>
    <form method="post" action="contact">
        <div>
            <label>First name: </label>
            <input type="text" name="firstName">
        </div>
        <div>
            <label>Last name: </label>
            <input type="text" name="lastName">
        </div>
        <div>
            <label>Subject</label></br>
            <input type="radio" name="subject" value="account"> account
            <input type="radio" name="subject" value="quiz">quiz
            <input type="radio" name="subject" value="other">other

        </div>
        </br>
        <label>Content</label>
        <div>
            <textarea type="text" name="content" ></textarea>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>

</html>
