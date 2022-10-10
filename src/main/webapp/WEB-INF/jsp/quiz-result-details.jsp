<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Quiz result</title>
</head>
<h3>Quiz Result</h3>

<body>
    <div>
        Question: ${quizQuestions[0].description}
        </br>
        Choices:
        <c:forEach items="${question1Choices}" var="choice">
            </br>
            ${choice.description}
        </c:forEach>
        </br>
        Selected:${selectedQ1Choice}
        Result: ${q1Result}
    </div>
    </br>
    <div>
            Question: ${quizQuestions[1].description}
            </br>
            Choices:
            <c:forEach items="${question2Choices}" var="choice">
                </br>
                ${choice.description}
            </c:forEach>
            </br>
            Selected:${selectedQ2Choice}
            Result: ${q2Result}
    </div>
    </br>
    <div>
        Question: ${quizQuestions[2].description}
        </br>
        Choices:
        <c:forEach items="${question3Choices}" var="choice">
            </br>
            ${choice.description}
        </c:forEach>
        </br>
        Selected: ${selectedQ3Choice}
        Result: ${q3Result}
    </div>
    </br>
    <div>
        Question: ${quizQuestions[3].description}
        </br>
        Choices:
        <c:forEach items="${question4Choices}" var="choice">
            </br>
            ${choice.description}
        </c:forEach>
        </br>
        Selected: ${selectedQ4Choice}
        Result: ${q4Result}
    </div>
    </br>
    <div>
        Question: ${quizQuestions[4].description}
        </br>
        Choices:
        <c:forEach items="${question5Choices}" var="choice">
            </br>
            ${choice.description}
        </c:forEach>
        </br>
        Selected: ${selectedQ5Choice}
        Result: ${q5Result}
    </div>
    </br>
    Start time: ${quizResult.startTime}
    </br>
    End time: ${quizResult.endTime}
    </br>
    Quiz result: ${quizResult.isPass()}
    </br>

</body>

</html>
