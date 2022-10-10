<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Quiz</title>
</head>

<body>
<h3>${selectedCategory} quiz</h3>
<div>
    <form method="post" action="/quiz-question4">
                Question:
                ${quizQuestions[3].description}
                </br>
                </br>
                Choose from:
                </br>
                <c:forEach items="${question4Choices}" var="choice">
                    <input type="radio" name="selectedQ4Choice" value="${choice.description}">${choice.description}
                </c:forEach>
                    </br>
                    <button type="submit"> save answer</button>
                </form>



                </br>
                <a href="quiz-question3">pre</a>
                <a href="quiz-question5">next</a>
                </br>

                <a href="quiz-question1" name="quizPage" value="0">1</a>
                <a href="quiz-question2" name="quizPage" value="1">2</a>
                <a href="quiz-question3" name="quizPage" value="2">3</a>
                <a href="quiz-question4" name="quizPage" value="3">4</a>
                <a href="quiz-question5" name="quizPage" value="4">5</a>
                </br>

            <form method="post" action="/quiz">
                <button type="submit">Submit</button>
            </form>
</div>
</body>

</html>
