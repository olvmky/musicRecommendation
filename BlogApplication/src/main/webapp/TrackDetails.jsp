<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SoundWave - Track Details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<style>
.track-info {
	background-color: #f8f9fa;
	padding: 20px;
	border-radius: 10px;
}
.section {
	margin-top: 30px;
}
.star-rating {
	color: #ffc107;
}
.mood-tag {
	display: inline-block;
	padding: 5px 10px;
	margin-right: 5px;
	border-radius: 5px;
	background-color: #e9ecef;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<h1 class="mb-4">Track Details</h1>

		<!-- Track Information Section -->
		<div class="track-info">
			<h2>${track.trackName}</h2>
			<p><strong>Album:</strong> ${track.albumName}</p>
			<p><strong>Genre:</strong> ${track.genreName}</p>
			<p><strong>Popularity:</strong> ${track.popularity}</p>
			<p><strong>Duration:</strong> <fmt:formatNumber value="${track.durationMs / 60000}" maxFractionDigits="0"/> min</p>
			<p><strong>Explicit Content:</strong> ${track.explicit ? 'Yes' : 'No'}</p>
			<p><strong>Danceability:</strong> ${track.danceability}</p>
			<p><strong>Energy:</strong> ${track.energy}</p>
			<p><strong>Tempo:</strong> ${track.tempo} BPM</p>
			<p><strong>Time Signature:</strong> ${track.timeSignature}</p>
		</div>

		<!-- Mood Tags Section -->
		<div class="section">
			<h3>Mood Tags</h3>
			<div>
				<c:forEach items="${moodTags}" var="moodTag">
					<span class="mood-tag" style="background-color: ${moodTagCount[moodTag.mood] > 5 ? '#007bff' : '#6c757d'}">
						${moodTag.mood} (${moodTagCount[moodTag.mood]})
					</span>
				</c:forEach>
			</div>
		</div>

		<!-- Like/Dislike Section -->
		<div class="section">
			<h3>Likes and Dislikes</h3>
			<p><strong>Likes:</strong> ${likeCount}</p>
			<p><strong>Dislikes:</strong> ${dislikeCount}</p>
		</div>

		<!-- Comments Section -->
		<div class="section">
			<h3>Comments</h3>
			<c:if test="${empty comments}">
				<p>No comments yet.</p>
			</c:if>
			<c:forEach items="${comments}" var="comment">
				<div class="comment">
					<p><strong>${comment.userName}:</strong> ${comment.content}</p>
					<p><small>Posted on <fmt:formatDate value="${comment.created}" pattern="MMM d, yyyy h:mm a"/></small></p>
				</div>
				<hr/>
			</c:forEach>
		</div>

		<!-- Add New Comment Section -->
		<div class="section">
			<h4>Leave a Comment</h4>
			<form action="addComment" method="post">
				<div class="mb-3">
					<label for="commentContent" class="form-label">Your Comment:</label>
					<textarea id="commentContent" name="content" class="form-control" rows="3" required></textarea>
				</div>
				<input type="hidden" name="trackId" value="${track.trackId}"/>
				<button type="submit" class="btn btn-primary">Submit Comment</button>
			</form>
		</div>

	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
