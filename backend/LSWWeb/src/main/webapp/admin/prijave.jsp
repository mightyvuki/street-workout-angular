<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Pregled prijava</title>
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

.table-dark {
	background-color: #1e1e1e !important;
	color: #f1f1f1 !important;
}

.table-dark th {
	background-color: #1f1f1f !important;
	border-color: #444;
}

.table-dark td {
	border-color: #333;
	background-color: #1f1f1f !important;
}

.table-hover tbody tr:hover {
	background-color: #333 !important;
}

.table td, .table th {
	vertical-align: middle;
	text-align: center;
}

.table-responsive {
	border-radius: 10px;
	overflow: hidden;
}
</style>
</head>
<body>
	<%@ include file="/fragments/adminHeader.jsp"%>

	<div class="container mt-5">
		<c:if test="${not empty success}">
			<div class="alert alert-success">${success}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<h2 class="section-title">Sve prijave</h2>
		<c:choose>
			<c:when test="${empty takmicenja}">
				<div class="alert alert-warning">Nema evidentiranih
					takmičenja.</div>
			</c:when>
			<c:otherwise>
				<form action="" method="get" class="mb-3">
				    <label for="takmicenjeSelect">Izaberi takmičenje:</label>
				    <select id="takmicenjeSelect" name="takmicenjeId" class="form-select" onchange="this.form.submit()">
				        <option value="">Sva takmičenja</option>
				        <c:forEach var="t" items="${takmicenja}">
				            <option value="${t.id}" ${izabranoTakmicenje != null && t.id == izabranoTakmicenje.id ? 'selected' : ''}>${t.naziv}</option>
				        </c:forEach>
				    </select>
				</form>

				<div class="table-responsive">
				    <table class="table table-dark table-hover">
				        <thead>
				            <tr>
				                <th>ID</th>
				                <th>Takmičar</th>
				                <th>Disciplina</th>
				                <th>Akcije</th>
				            </tr>
				        </thead>
				        <tbody>
				            <c:forEach var="p" items="${prijave}">
				                <tr>
				                    <td>${p.id}</td>
				                    <td>${p.korisnik.ime} ${p.korisnik.prezime}</td>
				                    <td>${p.datumPrijave}</td>
				                    <td>
				                        <a href="${pageContext.request.contextPath}/adminController/prijave/izmeni/${p.id}" class="btn btn-sm btn-custom">Izmeni</a>
				                        <a href="${pageContext.request.contextPath}/adminController/prijave/obrisi/${p.id}" class="btn btn-sm btn-danger" onclick="return confirm('Da li ste sigurni?')">Obriši</a>
				                    </td>
				                </tr>
				            </c:forEach>
				            <c:if test="${not empty izabranoTakmicenje and izabranoTakmicenje.datum ge danas}">
				            <tr>
						    <form action="${pageContext.request.contextPath}/adminController/prijave/sacuvaj" method="post">
						        <td>—</td>
						        <td>
						            <select name="korisnikId" class="form-select">
						                <c:forEach var="k" items="${korisnici}">
						                    <option value="${k.id}">${k.ime} ${k.prezime}</option>
						                </c:forEach>
						            </select>
						        </td>
						        <td colspan="2">
						            <input type="hidden" name="takmicenjeId" value="${izabranoTakmicenje.id}" />
						            <button type="submit" class="btn btn-sm btn-custom">Dodaj</button>
						        </td>
						    </form>
						</tr>
						</c:if>
				        </tbody>
				    </table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>