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
		<h2 class="section-title">${takmicenje.naziv}</h2>
		<div class="row">
		<c:if test="${not empty success}">
	    	<div class="alert alert-success">${success}</div>
		</c:if>
		<c:if test="${not empty error}">
	    	<div class="alert alert-danger">${error}</div>
		</c:if>
			<p>${takmicenje.opis}</p>
			<p>
				Mesto: <strong>${takmicenje.lokacija}</strong>
			</p>
			<p>
				Datum: <strong>${takmicenje.datum}</strong>
			</p>
			<p>
				Organizator: <strong>${takmicenje.organizator.ime} ${takmicenje.organizator.prezime}</strong>
			</p>
		</div>
		<br>
		<c:choose>
			<c:when test="${odrzano}">
			<c:forEach var="entry" items="${rezultati}">
			    <h3 class="section-title">${entry.key.naziv}</h3>
			    <div class="row">
			        <table class="table table-bordered table-dark text-center">
			            <thead>
			                <tr>
			                    <th>Ime i prezime</th>
			                    <th>Disciplina</th>
			                    <th>Rezultat</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:forEach var="r" items="${entry.value}">
			                    <tr>
			                        <td>${r.korisnik.ime} ${r.korisnik.prezime}</td>
			                        <td>${r.disciplina.naziv}</td>
			                        <td>${r.rezultat}</td>
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
			    </div>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="row">
					<table class="table table-bordered table-dark text-center">
						<thead>
							<tr>
								<th>Ime i prezime</th>
								<th>Datum prijave</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="p" items="${prijave}">
								<tr>
									<td>${p.korisnik.ime} ${p.korisnik.prezime}</td>
									<td>${p.datumPrijave}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br>
				<c:if test="${not empty ulogovan}">
					<a href="${pageContext.request.contextPath}/prijava/prijavi_se/${takmicenje.id}" class="btn btn-custom">Prijavi se</a>
					<a href="${pageContext.request.contextPath}/prijava/odjavi_se/${takmicenje.id}" class="btn btn-custom alert-danger">Odjavi se</a>
				</c:if>
				<c:if test="${empty ulogovan}">
					<p>Morate biti prijavljeni na sajt da biste se prijavili za takmičenje.</p>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>