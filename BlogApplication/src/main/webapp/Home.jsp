<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SoundWave - Home</title>
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
            <span id="successMessage"><b>${usermessages.success}</b></span>
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
    
    <form action="findtracks" method="post">
        <h1>Search for a Track by Title</h1>
        <p>
            <label for="tracktitle">Track Title</label>
            <input id="tracktitle" name="tracktitle" value="${fn:escapeXml(param.tracktitle)}">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${trackmessages.success}</b></span>
        </p>
    </form>
    <br/>
    <h1>Matching Tracks</h1>
    <table border="1">
        <tr>
            <th>Track ID</th>
            <th>Track Name</th>
            <th>Popularity</th>
            <th>Explicit</th>
            <th>Duration(ms)</th>
            <th>View Details</th>
        </tr>
        <c:forEach items="${tracks}" var="track" >
            <tr>
                <td><c:out value="${track.getTrackId()}" /></td>
                <td><c:out value="${track.getTrackName()}" /></td>
                <td><c:out value="${track.getPopularity()}" /></td>
                <td><c:out value="${track.isExplicit()}" /></td>
                <td><c:out value="${track.getDurationMs()}" /></td>
                <td><a href="trackdetails?trackid=<c:out value="${track.getTrackId()}"/>">View Details</a></td>
            </tr>
        </c:forEach>
    </table>
    
    <form action="findalbums" method="post">
        <h1>Search for a Album by Title</h1>
        <p>
            <label for="albumtitle">Album Title</label>
            <input id="albumtitle" name="albumtitle" value="${fn:escapeXml(param.albumtitle)}">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${albummessages.success}</b></span>
        </p>
    </form>
    <br/>
    <h1>Matching Albums</h1>
    <table border="1">
        <tr>
            <th>Album ID</th>
            <th>Album Name</th>
            <th>View Details</th>
        </tr>
        <c:forEach items="${albums}" var="album" >
            <tr>
                <td><c:out value="${album.getAlbumId()}" /></td>
                <td><c:out value="${album.getAlbumName()}" /></td>
                <td><a href="albumdetails?albumid=<c:out value="${album.getAlbumId()}"/>">View Details</a></td>
            </tr>
        </c:forEach>
    </table>
    
    
</body>
</html>
