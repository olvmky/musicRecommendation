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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
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

.pagination-container {
	display: flex;
	align-items: center;
	justify-content: center;
}

.pagination-container input {
	width: 50px;
	text-align: center;
}

.star-rating {
	color: #ffc107;
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
					<form action="findusers" method="post" class="mb-3" id="usersForm">
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
					<form action="findtracks" method="post" class="mb-3"
						id="tracksForm">
						<input type="hidden" name="activeTab" value="tracks">
						<div class="input-group">
							<input type="text" class="form-control" id="tracktitle"
								name="tracktitle" placeholder="Enter track title"
								value="${fn:escapeXml(param.tracktitle)}"> <select
								class="form-select" id="mood" name="mood"
								style="max-width: 150px;">
								<option value="" title="Any">Select mood</option>
								<option value="HAPPY" ${param.mood == 'HAPPY' ? 'selected' : ''}
									title="Happy">😊 Happy</option>
								<option value="SAD" ${param.mood == 'SAD' ? 'selected' : ''}
									title="Sad">😢 Sad</option>
								<option value="RELAXED"
									${param.mood == 'RELAXED' ? 'selected' : ''} title="Relaxed">😌 Relaxed</option>
								<option value="EXCITED"
									${param.mood == 'EXCITED' ? 'selected' : ''} title="Excited">🤩 Excited</option>
								<option value="ROMANTIC"
									${param.mood == 'ROMANTIC' ? 'selected' : ''} title="Romantic">❤️ Romantic</option>
								<option value="ANGRY" ${param.mood == 'ANGRY' ? 'selected' : ''}
									title="Angry">😠 Angry</option>
							</select>
							<button class="btn btn-primary" type="submit">Search
								Tracks</button>
						</div>
					</form>
				</div>
				<div
					class="tab-pane fade ${param.activeTab == 'albums' ? 'show active' : ''}"
					id="albums" role="tabpanel">
					<form action="findalbums" method="post" class="mb-3"
						id="albumsForm">
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

				<div class="pagination-container mt-3">
					<button class="btn btn-secondary me-2"
						onclick="changePage('users', -1)">
						<i class="fas fa-chevron-left"></i>
					</button>
					<span>Page</span> <input type="number" id="currentPageUsers"
						value="${currentPage}" min="1" max="${totalPages}"
						class="form-control mx-2" onchange="goToPage('users', this.value)">
					<span>of ${totalPages}</span>
					<button class="btn btn-secondary ms-2"
						onclick="changePage('users', 1)">
						<i class="fas fa-chevron-right"></i>
					</button>
				</div>
			</c:if>

			<c:if test="${not empty tracks}">
				<h3>Matching Tracks</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Track Name</th>
							<th>Popularity</th>
							<th>Explicit</th>
							<th>Duration</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tracks}" var="track">
							<tr>
								<td><c:out value="${track.getTrackName()}" /></td>
								<td><span class="star-rating"> <c:forEach begin="1"
											end="5" var="i">
											<c:choose>
												<c:when test="${i <= track.getPopularity() / 20}">&#9733;</c:when>
												<c:otherwise>&#9734;</c:otherwise>
											</c:choose>
										</c:forEach>
								</span></td>
								<td><c:out value="${track.isExplicit() ? 'Yes' : 'No'}" /></td>
								<td><fmt:formatNumber
										value="${track.getDurationMs() / 60000}" pattern="#0" />:<fmt:formatNumber
										value="${track.getDurationMs() % 60000 / 1000}" pattern="00" />
								</td>
								<td><a
									href="trackdetails?trackid=<c:out value="${track.getTrackId()}"/>"
									class="btn btn-sm btn-info">View Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="pagination-container mt-3">
					<button class="btn btn-secondary me-2"
						onclick="changePage('tracks', -1)">
						<i class="fas fa-chevron-left"></i>
					</button>
					<span>Page</span> <input type="number" id="currentPageTracks"
						value="${currentPage}" min="1" max="${totalPages}"
						class="form-control mx-2"
						onchange="goToPage('tracks', this.value)"> <span>of
						${totalPages}</span>
					<button class="btn btn-secondary ms-2"
						onclick="changePage('tracks', 1)">
						<i class="fas fa-chevron-right"></i>
					</button>
				</div>
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

				<div class="pagination-container mt-3">
					<button class="btn btn-secondary me-2"
						onclick="changePage('albums', -1)">
						<i class="fas fa-chevron-left"></i>
					</button>
					<span>Page</span> <input type="number" id="currentPageAlbums"
						value="${currentPage}" min="1" max="${totalPages}"
						class="form-control mx-2"
						onchange="goToPage('albums', this.value)"> <span>of
						${totalPages}</span>
					<button class="btn btn-secondary ms-2"
						onclick="changePage('albums', 1)">
						<i class="fas fa-chevron-right"></i>
					</button>
				</div>
			</c:if>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        function changePage(type, delta) {
            let currentPage = parseInt(document.getElementById('currentPage' + type.charAt(0).toUpperCase() + type.slice(1)).value);
            let newPage = currentPage + delta;
            if (newPage >= 1 && newPage <= ${totalPages}) {
                goToPage(type, newPage);
            }
        }

        function goToPage(type, page) {
            document.getElementById('currentPage' + type.charAt(0).toUpperCase() + type.slice(1)).value = page;
            let form = document.getElementById(type + 'Form');
            let pageInput = document.createElement('input');
            pageInput.type = 'hidden';
            pageInput.name = 'page';
            pageInput.value = page;
            form.appendChild(pageInput);
            form.submit();
        }
    </script>
</body>
</html>
