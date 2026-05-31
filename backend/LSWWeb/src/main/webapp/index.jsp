<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Street Workout Lovćenac</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
	body {
	background-color: #121212;
	color: #f1f1f1;
	font-family: 'Segoe UI', sans-serif;
}

header {
	background-color: #1f1f1f;
	padding: 1rem 2rem;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

header h1 {
	font-size: 1.5rem;
	color: #f5f5f5;
	margin: 0;
}

.auth-links a {
	color: #f5f5f5;
	text-decoration: none;
	margin-left: 1rem;
}

.auth-links a:hover {
	text-decoration: underline;
}

.container {
	margin: 4rem;
}

.section-title {
	border-bottom: 2px solid #444;
	margin-bottom: 1rem;
	padding-bottom: 0.5rem;
}

.card {
	background-color: #2a2a2a;
	border: none;
	border-radius: 10px;
	color: #f1f1f1;
	transition: transform 0.2s ease;
}

.card:hover {
	transform: scale(1.02);
}

.btn-custom {
	background-color: #444;
	border: none;
	color: #f1f1f1;
}

.btn-custom:hover {
	background-color: #666;
}
</style>
</head>
<body>
	<%@ include file="/fragments/header.jsp"%>

	<div class="container">
		<h2 class="section-title">Najavljena takmičenja</h2>
		<div class="row">
			<c:choose>
				<c:when test="${empty najavljenaTakmicenja}">
					<div class="alert alert-danger">Nema najavljenih takmičenja</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="t" items="${najavljenaTakmicenja}">
						<div class="col-md-4 mb-4">
							<div class="card p-3">
								<h5>${t.naziv}</h5>
								<p>
									<strong>Lokacija:</strong> ${t.lokacija}
								</p>
								<p>
									<strong>Datum:</strong> ${t.datum}
								</p>
								<p>${t.opis}</p>
								<a href="${pageContext.request.contextPath}/takmicenje/prijava/${t.id}" class="btn btn-custom">Detaljnije</a>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>

		<!-- Održana takmičenja -->
		<h2 class="section-title mt-5">Održana takmičenja</h2>
		<div class="row">
			<c:choose>
				<c:when test="${empty odrzanaTakmicenja}">
					<div class="alert alert-danger">Nema odrzanih takmičenja</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="t" items="${odrzanaTakmicenja}">
						<div class="col-md-4 mb-4">
							<div class="card p-3">
								<h5>${t.naziv}</h5>
								<p>
									<strong>Lokacija:</strong> ${t.lokacija}
								</p>
								<p>
									<strong>Datum:</strong> ${t.datum}
								</p>
								<p>${t.opis}</p>
								<a href="${pageContext.request.contextPath}/takmicenje/rezultati/${t.id}" class="btn btn-custom">Detaljnije</a>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>