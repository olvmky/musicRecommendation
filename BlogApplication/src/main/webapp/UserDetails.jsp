<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body>
	<h1>User Details</h1>
	<table border="1">
		<tr>
			<th>UserName</th>
			<td><c:out value="${user.getUserName()}" /></td>
		</tr>
		<tr>
			<th>FirstName</th>
			<td><c:out value="${user.getFirstName()}" /></td>
		</tr>
		<tr>
			<th>LastName</th>
			<td><c:out value="${user.getLastName()}" /></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${user.getEmail()}" /></td>
		</tr>
		<tr>
			<th>Phone</th>
			<td><c:out value="${user.getPhone()}" /></td>
		</tr>
	</table>

	<h2>Listening History</h2>
	<table border="1">
		<tr>
			<th>Track</th>
			<th>Times Listened</th>
			<th>Duration (seconds)</th>
			<th>Last Listened</th>
		</tr>
		<c:forEach items="${listeningHistory}" var="history">
			<tr>
				<td><c:out value="${history.getTrackId()}" /></td>
				<td><c:out value="${history.getTimesListened()}" /></td>
				<td><c:out value="${history.getDuration()}" /></td>
				<td><fmt:formatDate value="${history.getCreated()}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
	</table>

	<h2>Playlists</h2>
	<table border="1">
		<tr>
			<th>Playlist Name</th>
			<th>Created</th>
			<th>Is Public</th>
			<th>View Tracks</th>
		</tr>
		<c:forEach items="${playlists}" var="playlist">
			<tr>
				<td><c:out value="${playlist.getName()}" /></td>
				<td><fmt:formatDate value="${playlist.getCreated()}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><c:out value="${playlist.isIsPublic()}" /></td>
				<td><a
					href="playlisttracks?playlistid=<c:out value="${playlist.getPlaylistId()}"/>">View
						Tracks</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
