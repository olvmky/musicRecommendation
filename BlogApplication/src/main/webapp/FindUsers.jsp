<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Users</title>
</head>
<body>
    <form action="findusers" method="post">
        <h1>Search for a User by Name</h1>
        <p>
            <label for="name">Name</label>
            <input id="name" name="name" value="${fn:escapeXml(param.name)}">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
    </form>
    <br/>
    <h1>Matching Users</h1>
    <table border="1">
        <tr>
            <th>UserName</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Phone</th>
            <th>View Details</th>
        </tr>
        <c:forEach items="${users}" var="user" >
            <tr>
                <td><c:out value="${user.getUserName()}" /></td>
                <td><c:out value="${user.getFirstName()}" /></td>
                <td><c:out value="${user.getLastName()}" /></td>
                <td><c:out value="${user.getEmail()}" /></td>
                <td><c:out value="${user.getPhone()}" /></td>
                <td><a href="userdetails?username=<c:out value="${user.getUserName()}"/>">View Details</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
