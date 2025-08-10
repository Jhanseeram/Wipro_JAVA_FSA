<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>City List</title>
</head>
<body>

    <h1>List of Cities in India</h1>

    <ul>
        <c:forEach var="city" items="${citilist}">
            <li>${city}</li>
        </c:forEach>
    </ul>

</body>
</html>
