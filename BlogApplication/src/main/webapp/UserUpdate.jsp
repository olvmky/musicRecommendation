<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>
	<h1>Update User</h1>
	<form action="userupdate" method="post">
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}" readonly>		</p>
		<p>
			<label for="password">Password</label>
			<input id="password" name="password" value="${user.password}">
		</p>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${user.firstName}">
		</p>
		<p>
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="${user.lastName}"">
		</p>
		<p>
			<label for="email">Email</label>
			<input id="email" name="email" value="${user.email}"">
		</p>
		<p>
			<label for="phone">Phone</label>
			<input id="phone" name="phone" value="${user.phone}"">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>