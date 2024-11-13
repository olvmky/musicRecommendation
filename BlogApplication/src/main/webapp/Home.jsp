<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SoundWave - Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	padding-top: 20px;
}

.search-section {
	margin-bottom: 30px;
}

.results-section {
	margin-top: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center mb-4">Welcome to SoundWave</h1>

		<div class="search-section">
			<h2>Search</h2>
			<ul class="nav nav-tabs" id="searchTabs" role="tablist">
				<li class="nav-item" role="presentation">
					<button
						class="nav-link ${param.activeTab == 'users' || param.activeTab == null ? 'active' : ''}"
						id="users-tab" data-bs-toggle="tab" data-bs-target="#users"
						type="button" role="tab">Users</button>
				</li>
				<li class="nav-item" role="presentation">
					<button
						class="nav-link ${param.activeTab == 'tracks' ? 'active' : ''}"
						id="tracks-tab" data-bs-toggle="tab" data-bs-target="#tracks"
						type="button" role="tab">Tracks</button>
				</li>
				<li class="nav-item" role="presentation">
					<button
						class="nav-link ${param.activeTab == 'albums' ? 'active' : ''}"
						id="albums-tab" data-bs-toggle="tab" data-bs-target="#albums"
						type="button" role="tab">Albums</button>
				</li>
			</ul>
			<div class="tab-content mt-3" id="searchTabsContent">
				<div
					class="tab-pane fade ${param.activeTab == 'users' || param.activeTab == null ? 'show active' : ''}"
					id="users" role="tabpanel">
					<form action="findusers" method="post" class="mb-3">
						<input type="hidden" name="activeTab" value="users">
						<div class="input-group">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="Enter user name"
								value="${fn:escapeXml(param.name)}">
							<button class="btn btn-primary" type="submit">Search
								Users</button>
						</div>
					</form>
				</div>
				<div
					class="tab-pane fade ${param.activeTab == 'tracks' ? 'show active' : ''}"
					id="tracks" role="tabpanel">
					<form action="findtracks" method="post" class="mb-3">
						<input type="hidden" name="activeTab" value="tracks">
						<div class="input-group">
							<input type="text" class="form-control" id="tracktitle"
								name="tracktitle" placeholder="Enter track title"
								value="${fn:escapeXml(param.tracktitle)}">
							<button class="btn btn-primary" type="submit">Search
								Tracks</button>
						</div>
					</form>
				</div>
				<div
					class="tab-pane fade ${param.activeTab == 'albums' ? 'show active' : ''}"
					id="albums" role="tabpanel">
					<form action="findalbums" method="post" class="mb-3">
						<input type="hidden" name="activeTab" value="albums">
						<div class="input-group">
							<input type="text" class="form-control" id="albumtitle"
								name="albumtitle" placeholder="Enter album title"
								value="${fn:escapeXml(param.albumtitle)}">
							<button class="btn btn-primary" type="submit">Search
								Albums</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="results-section">
			<h2>Search Results</h2>

			<c:if test="${not empty users}">
				<h3>Matching Users</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>UserName</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td><c:out value="${user.getUserName()}" /></td>
								<td><c:out value="${user.getFirstName()}" /></td>
								<td><c:out value="${user.getLastName()}" /></td>
								<td><c:out value="${user.getEmail()}" /></td>
								<td><c:out value="${user.getPhone()}" /></td>
								<td><a
									href="userdetails?username=<c:out value="${user.getUserName()}"/>"
									class="btn btn-sm btn-info">View Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${not empty tracks}">
				<h3>Matching Tracks</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Track ID</th>
							<th>Track Name</th>
							<th>Popularity</th>
							<th>Explicit</th>
							<th>Duration(ms)</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tracks}" var="track">
							<tr>
								<td><c:out value="${track.getTrackId()}" /></td>
								<td><c:out value="${track.getTrackName()}" /></td>
								<td><c:out value="${track.getPopularity()}" /></td>
								<td><c:out value="${track.isExplicit()}" /></td>
								<td><c:out value="${track.getDurationMs()}" /></td>
								<td><a
									href="trackdetails?trackid=<c:out value="${track.getTrackId()}"/>"
									class="btn btn-sm btn-info">View Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<c:if test="${not empty albums}">
				<h3>Matching Albums</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Album ID</th>
							<th>Album Name</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${albums}" var="album">
							<tr>
								<td><c:out value="${album.getAlbumId()}" /></td>
								<td><c:out value="${album.getAlbumName()}" /></td>
								<td><a
									href="albumdetails?albumid=<c:out value="${album.getAlbumId()}"/>"
									class="btn btn-sm btn-info">View Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
