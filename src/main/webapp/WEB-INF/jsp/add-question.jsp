<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Add Question</title>
</head>

<body>
    <h3>Add question</h3>
    <div>
        <form method="post" action="/add-question">
            <label>category</label>
            <input type="text" name="category">
            <div>
                <label>description</label>
                <input type="text" name="description">
            </div>
            <div>
                <label>choice1</label>
                <input type="text" name="choice1">
                <input type="radio"
                       name="answer"
                       value="0"/>
            </div>
            <div>
                <label>choice2</label>
                <input type="text" name="choice2">
                <input type="radio"
                       name="answer"
                       value="1"/>
            </div>
            <div>
                <label>choice3</label>
                <input type="text" name="choice3">
                <input type="radio"
                       name="answer"
                       value="2"/>
            </div>
            <div>
                <label>choice4</label>
                <input type="text" name="choice4">
                <input type="radio"
                       name="answer"
                       value="3"/>
            </div>
            <div>
                <label>choice5</label>
                <input type="text" name="choice5">
                <input type="radio"
                       name="answer"
                       value="4"/>
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
