<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Quiz</title>
</head>

<body>
<h3>${selectedCategory} quiz</h3>
<div>
    <form method="post" action="/quiz">
        You have 15 mins to take the quiz

        <a href="quiz-question1" name="quizStartTime" value="0">Start the quiz</a>
        </br>
    </form>
</div>
</body>

</html>
