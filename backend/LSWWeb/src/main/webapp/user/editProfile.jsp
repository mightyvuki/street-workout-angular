<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Izmena profila | Street Workout Lovćenac</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #1f1f1f;
	color: #f1f1f1;
	font-family: 'Segoe UI', sans-serif;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.edit-box {
	background-color: #2a2a2a;
	padding: 2rem 3rem;
	border-radius: 10px;
	box-shadow: 0 0 10px #000;
	width: 100%;
	max-width: 500px;
}

.btn-custom {
	background-color: #444;
	color: #f1f1f1;
	border: none;
}

.btn-custom:hover {
	background-color: #666;
}

a {
	color: #bbb;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.form-label {
	color: #ccc;
}
</style>
</head>
<body>

	<div class="edit-box">
		<h2 class="text-center mb-4">Izmeni profil</h2>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<c:if test="${not empty success}">
			<div class="alert alert-success">${success}</div>
		</c:if>
		<form:form modelAttribute="korisnik"
			action="${pageContext.request.contextPath}/userController/updateProfile"
			method="post">
			<form:hidden path="id" />

			<div class="mb-3">
				<label class="form-label" for="ime">Ime:</label>
				<form:input path="ime" id="ime" class="form-control"
					required="required" />
			</div>

			<div class="mb-3">
				<label class="form-label" for="prezime">Prezime:</label>
				<form:input path="prezime" id="prezime" class="form-control"
					required="required" />
			</div>

			<div class="mb-3">
				<label class="form-label" for="username">Korisničko ime:</label>
				<form:input path="username" id="username" class="form-control"
					required="required" />
			</div>

			<div class="mb-3">
				<label class="form-label" for="email">Email:</label>
				<form:input path="email" id="email" class="form-control"
					required="required" />
			</div>

			<div class="mb-3">
				<label class="form-label" for="password">Nova lozinka
					(opciono):</label>
				<form:password path="password" id="password" class="form-control"
					placeholder="Ostavi prazno ako ne menjaš" />
			</div>

			<div class="mb-3">
				<label class="form-label" for="pol">Pol:</label>
				<form:select path="pol" id="pol" class="form-select">
					<form:option value="muško" label="Muško" />
					<form:option value="žensko" label="Žensko" />
					<form:option value="drugo" label="Drugo" />
				</form:select>
			</div>

			<div class="mb-3">
				<label class="form-label" for="datumRodjenja">Datum rođenja:</label>
				<form:input path="datumRodjenja" id="datumRodjenja" type="date"
					class="form-control" required="required" />
			</div>

			<button type="submit" class="btn btn-custom w-100">Sačuvaj
				izmene</button>
		</form:form>

		<div class="mt-3 text-center">
			<a href="${pageContext.request.contextPath}/">Povratak na početnu</a>
		</div>
	</div>

</body>
</html>
