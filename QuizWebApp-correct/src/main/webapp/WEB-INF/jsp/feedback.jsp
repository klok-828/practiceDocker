<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Feedback</title>
</head>

<body>
<h1>Feedback</h1>
<div>
    <form method="post" action="feedback">
        <div>
            <label>rating</label>
            <input type="radio" name="rating" value=1> 1
            <input type="radio" name="rating" value=2>2
            <input type="radio" name="rating" value=3>3
            <input type="radio" name="rating" value=4>4
            <input type="radio" name="rating" value=5>5
        </div>
        <label>Content</label>
        <div>
            <textarea type="text" name="content" ></textarea>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
</body>

</html>
