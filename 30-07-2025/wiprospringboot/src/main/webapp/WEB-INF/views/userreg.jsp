<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration</h2>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>

    <form:form action="register" modelAttribute="user" method="post">
        Username: <form:input path="username" /><br><br>
        Email: <form:input path="userEmail" /><br><br>
        Phone: <form:input path="userPhone" /><br><br>
        <input type="submit" value="Register">
    </form:form>
</body>
</html>
