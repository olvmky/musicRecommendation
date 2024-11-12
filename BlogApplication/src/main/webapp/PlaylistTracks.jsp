<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Playlist Tracks</title>
</head>
<body>
    <h1>Playlist: ${playlist.getName()}</h1>
    <table border="1">
        <tr>
            <th>Track Name</th>
            <th>Duration (ms)</th>
            <th>Popularity</th>
            <th>Explicit</th>
        </tr>
        <c:forEach items="${tracks}" var="track" >
            <tr>
                <td><c:out value="${track.getTrackName()}" /></td>
                <td><c:out value="${track.getDurationMs()}" /></td>
                <td><c:out value="${track.getPopularity()}" /></td>
                <td><c:out value="${track.isExplicit()}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
