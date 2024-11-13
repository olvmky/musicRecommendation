<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SoundWave - Track Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
	padding-top: 20px;
}

.search-section, .results-section, .card {
	background-color: #ffffff;
	border-radius: 15px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 30px;
	overflow: hidden;
}

.pagination-container {
	display: flex;
	align-items: center;
	justify-content: center;
}

.pagination-container input {
	width: 60px;
	text-align: center;
}

.star-rating {
	color: #ffc107;
}

.card-header {
	background-color: rgba(0, 123, 255, 0.1);
	border-bottom: 1px solid #eee;
	margin: -20px -20px 20px -20px;
	padding: 15px 20px;
}

.card-title {
	margin-bottom: 0;
	color: #333;
	font-size: 1.25rem;
	font-weight: 600;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center mb-4">Track Details</h1>

		<!-- Track Information Section -->
		<div class="card">
			<div class="card-header">
				<h2 class="card-title">Track Information</h2>
			</div>
			<div class="card-body">
				<h3>${trackDetails.track.trackName}</h3>
				<p>
					<strong>Album:</strong> ${trackDetails.albumName}
				</p>
				<p>
					<strong>Genre:</strong> ${trackDetails.genreName}
				</p>
				<p>
					<strong>Popularity:</strong> ${trackDetails.track.popularity}
				</p>
				<p>
					<strong>Duration:</strong>
					<fmt:formatNumber value="${trackDetails.track.durationMs / 60000}"
						maxFractionDigits="0" />
					min
				</p>
				<p>
					<strong>Explicit Content:</strong> ${trackDetails.track.explicit ? 'Yes' : 'No'}
				</p>
				<p>
					<strong>Danceability:</strong> ${trackDetails.track.danceability}
				</p>
				<p>
					<strong>Energy:</strong> ${trackDetails.track.energy}
				</p>
				<p>
					<strong>Tempo:</strong> ${trackDetails.track.tempo} BPM
				</p>
				<p>
					<strong>Time Signature:</strong>
					${trackDetails.track.timeSignature}
				</p>
			</div>
		</div>

		<!-- Mood Tags Section -->
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">Mood Tags</h3>
			</div>
			<div class="card-body">
				<c:forEach items="${trackDetails.moodTags}" var="moodTag">
					<span class="mood-tag">${moodTag.mood}</span>
				</c:forEach>
			</div>
		</div>

		<!-- Like/Dislike Section -->
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">Likes and Dislikes</h3>
			</div>
			<div class="card-body">
				<p>
					<strong>Likes:</strong> ${trackDetails.likeCount}
				</p>
				<p>
					<strong>Dislikes:</strong> ${trackDetails.dislikeCount}
				</p>
			</div>
		</div>

		<!-- Comments Section -->
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">Comments</h3>
			</div>
			<div class="card-body">
				<c:if test="${empty trackDetails.comments}">
					<p>No comments yet.</p>
				</c:if>
				<c:forEach items="${trackDetails.comments}" var="comment">
					<div class="comment mb-3">
						<p>
							<strong>${comment.userName}:</strong> ${comment.content}
						</p>
						<p>
							<small>Posted on <fmt:formatDate
									value="${comment.created}" pattern="MMM d, yyyy h:mm a" /></small>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- Add New Comment Section -->
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">Leave a Comment</h3>
			</div>
			<div class="card-body">
				<form action="addComment" method="post">
					<div class="mb-3">
						<label for="commentContent" class="form-label">Your
							Comment:</label>
						<textarea id="commentContent" name="content" class="form-control"
							rows="3" required></textarea>
					</div>
					<input type="hidden" name="trackId"
						value="${trackDetails.track.trackId}" />
					<button type="submit" class="btn btn-primary">Submit
						Comment</button>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
