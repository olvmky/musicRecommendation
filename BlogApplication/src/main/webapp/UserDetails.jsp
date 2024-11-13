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
<title>SoundWave - User Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
<style>
.user-info {
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
</style>
</head>
<body>
	<div class="container mt-4">
		<h1 class="mb-4">User Profile</h1>

		<div class="user-info">
			<h2>${user.firstName}${user.lastName}</h2>
			<p>
				<i class="fas fa-user"></i> ${user.userName}
			</p>
			<p>
				<i class="fas fa-envelope"></i> ${user.email}
			</p>
			<p>
				<i class="fas fa-phone"></i> ${user.phone}
			</p>
		</div>

		<div class="section">
			<h2>Recommended Tracks</h2>
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Track Name</th>
							<th>Artist</th>
							<th>Popularity</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${recommendedTracks}" var="track">
							<tr>
								<td>${track.trackName}</td>
								<td>${track.artistName}</td>
								<td><span class="star-rating"> <c:forEach begin="1"
											end="${track.stars}">&#9733;</c:forEach> <c:forEach
											begin="${track.stars + 1}" end="5">&#9734;</c:forEach>
								</span></td>
								<td><a href="trackdetails?trackid=${track.trackId}"
									class="btn btn-primary btn-sm">View Track</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="section">
			<h2>Listening History</h2>
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Track</th>
							<th>Times Listened</th>
							<th>Duration</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listeningHistory}" var="history">
							<tr>
								<td>${history.trackName}</td>
								<td>${history.timesListened}</td>
								<td><fmt:formatNumber value="${history.duration / 60}"
										maxFractionDigits="0" /> min</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="section">
			<h2>Playlists</h2>
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Playlist Name</th>
							<th>Created</th>
							<th>Visibility</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${playlists}" var="playlist">
							<tr>
								<td>${playlist.name}</td>
								<td><fmt:formatDate value="${playlist.created}"
										pattern="MMM d, yyyy" /></td>
								<td><c:choose>
										<c:when test="${playlist.isPublic}">
											<span class="badge bg-success">Public</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-secondary">Private</span>
										</c:otherwise>
									</c:choose></td>
								<td><a
									href="playlisttracks?playlistid=${playlist.playlistId}"
									class="btn btn-primary btn-sm">View Tracks</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
