<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
</head>

<body>
    <h1>Admin</h1>

    <div>
        <h3>Quiz Result</h3>
        <form method="post" action="admin">
            <select name="categoryFilter">
                <option value="all" selected>all</option>
                <c:forEach items="${questionCategory}" var="category">
                    <option>${category}</option>
                </c:forEach>
            </select>

            <select name="userFilter">
                <option value=0 selected>all</option>
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.id}</option>
                </c:forEach>
            </select>
            <button type="submit"> search </button>
        </form>
        <table>
            <thead>
                <tr>
                    <th>userId</th>
                    <th>category</th>
                    <th>date</th>
                    <th>result</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${quizResults}" var="quizResult">
                <tr>
                    <td>${quizResult.userId}</td>
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

    <div>
        <h3>Users</h3>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Password</th>
                <th>Phone</th>
                <th>Address</th>
                <th>isActive</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.phone}</td>
                    <td>${user.address}</td>
                    <td>${user.isActive()}</td>
                    <td>
                        <a href="admin/user/${user.id}">
                            <c:choose>
                                <c:when test="${user.isActive() == true}">suspend</c:when>
                                <c:otherwise>active</c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <div>
        <h3>Quiz Questions</h3>

        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>category</th>
                <th>description</th>
                <th>isActive</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <td>${question.id}</td>
                    <td>${question.category}</td>
                    <td>${question.description}</td>
                    <td>${question.isActive()}</td>
                    <td>
                        <a href="admin/question/${question.id}" >
                            <c:choose>
                                <c:when test="${question.isActive() == true}">suspend</c:when>
                                <c:otherwise>active</c:otherwise>
                             </c:choose>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="add-question" >
        add
        </a>
    </div>

    <div>
        <h3>Feedback</h3>
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>rating</th>
                <th>content</th>
                <th>date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${feedbacks}" var="feedback">
                <tr>
                    <td>${feedback.id}</td>
                    <td>${feedback.rating}</td>
                    <td>${feedback.content}</td>
                    <td>${feedback.fb_date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div>
        <h3>Contact us</h3>
        <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>firstName</th>
                        <th>lastName</th>
                        <th>subject</th>
                        <th>content</th>
                        <th>userId</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${contacts}" var="contact">
                        <tr>
                            <td>${contact.id}</td>
                            <td>${contact.firstName}</td>
                            <td>${contact.lastName}</td>
                            <td>${contact.subject}</td>
                            <td>${contact.content}</td>
                            <td>${contact.user_id}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </div>

</body>

</html>