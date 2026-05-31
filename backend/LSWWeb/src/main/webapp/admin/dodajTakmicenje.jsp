<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Dodaj novo takmičenje | Street Workout Lovćenac</title>
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

.add-box {
	background-color: #2a2a2a;
	padding: 2rem 3rem;
	border-radius: 10px;
	box-shadow: 0 0 10px #000;
	width: 100%;
	max-width: 600px;
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

.error {
	color: red;
}
</style>
</head>
<body>

	<div class="add-box">
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<c:if test="${not empty success}">
			<div class="alert alert-success">${success}</div>
		</c:if>

        <c:set var="formAction" value=""/>
        <h2 class="text-center mb-4">Dodaj novo takmičenje</h2>
		
		<form:form modelAttribute="takmicenje" action="${pageContext.request.contextPath}/adminController/takmicenja/sacuvaj" method="post">
		    <form:hidden path="id"/>
		
		    <div class="mb-3">
		        <label class="form-label" for="naziv">Naziv takmičenja:</label>
		        <form:input path="naziv" id="naziv" class="form-control" />
		        <form:errors path="naziv" cssClass="error" />
		    </div>
		
		    <div class="mb-3">
		        <label class="form-label" for="datum">Datum:</label>
		        <form:input path="datum" id="datum" type="date" class="form-control" />
		        <form:errors path="datum" cssClass="error" />
		    </div>
		
		    <div class="mb-3">
		        <label class="form-label" for="lokacija">Lokacija:</label>
		        <form:input path="lokacija" id="lokacija" class="form-control" />
		        <form:errors path="lokacija" cssClass="error" />
		    </div>
		
		    <div class="mb-3">
		        <label class="form-label" for="opis">Opis:</label>
		        <form:textarea path="opis" id="opis" class="form-control" rows="4" placeholder="Unesi kratak opis takmičenja..."/>
		    </div>
		
		    <button type="submit" class="btn btn-custom w-100">Sačuvaj takmičenje</button>
		</form:form>

		<div class="mt-3 text-center">
			<a href="${pageContext.request.contextPath}/adminController/takmicenja">Povratak na listu takmičenja</a>
		</div>
	</div>

</body>
</html>
