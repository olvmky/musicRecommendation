<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
            <td><c:out value="${user.userName}" /></td>
        </tr>
        <tr>
            <th>FirstName</th>
            <td><c:out value="${user.firstName}" /></td>
        </tr>
        <tr>
            <th>LastName</th>
            <td><c:out value="${user.lastName}" /></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${user.email}" /></td>
        </tr>
        <tr>
            <th>Phone</th>
            <td><c:out value="${user.phone}" /></td>
        </tr>
    </table>

    <h2>Recommended Tracks</h2>
	<table border="1">
	    <tr>
	        <th>Track Name</th>
	        <th>Artist</th>
	        <th>Popularity</th>
	    </tr>
	    <c:forEach items="${recommendedTracks}" var="track" >
	        <tr>
	            <td><c:out value="${track.trackName}" /></td>
	            <td><c:out value="${track.artistName}" /></td>
	            <td>
	                <c:forEach begin="1" end="${track.stars}">&#9733;</c:forEach>
	                <c:forEach begin="${track.stars + 1}" end="5">&#9734;</c:forEach>
	            </td>
	        </tr>
	    </c:forEach>
	</table>

	<h2>Listening History</h2>
	<table border="1">
		<tr>
			<th>Track</th>
			<th>Times Listened</th>
			<th>Duration (seconds)</th>
		</tr>
		<c:forEach items="${listeningHistory}" var="history">
			<tr>
				<td><c:out value="${history.getTrackName()}" /></td>
				<td><c:out value="${history.getTimesListened()}" /></td>
				<td><c:out value="${history.getDuration()}" /></td>
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
