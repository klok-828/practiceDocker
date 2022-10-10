<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<%@include file="head.jsp"%>
    <title>Home</title>
</head>

<body>
    <div>

        <h1>Home</h1>

        <h2>Choose one category to start quiz</h2>
        <form method="get" action="/quiz">
            <c:forEach items="${questionCategory}" var="category">
              <input id=${category} type="radio" name="selectedCategory" value=${category} /> ${category}

            </c:forEach>
            </br>
            <input type="submit" value="take quiz">
        </form>
    </div>

    <div>
        <h2>Quiz Result</h2>
           <table>
               <thead>
               <tr>
                   <th>category</th>
                   <th>date</th>
                   <th>result</th>
               </tr>
               </thead>
               <tbody>
               <c:forEach items="${quizResults}" var="quizResult">
                    <tr>
                        <td>${quizResult.category}</td>
                        <td>${quizResult.startTime}</td>
                        <td>
                            <c:choose>
                                <c:when test="${quizResult.isPass() == true}">pass</c:when>
                                <c:otherwise>fail</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="quizDetails/${quizResult.id}">
                            detail
                        </td>
                    </tr>
               </c:forEach>
               </tbody>
           </table>

    </div>

</body>

</html>
