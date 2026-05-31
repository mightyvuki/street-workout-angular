<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Lista takmičara</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	background-color: #121212;
	color: #f1f1f1;
	font-family: 'Segoe UI', sans-serif;
}

.container {
	margin: 4rem auto;
	max-width: 600px;
}

h2 {
	margin-bottom: 2rem;
	text-align: center;
}

.btn-primary {
	background-color: #444;
	border: none;
}

.btn-primary:hover {
	background-color: #666;
}

.auth-links a {
    color: #f5f5f5;
    text-decoration: none;
    margin-left: 1rem;
}

.auth-links a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="/fragments/adminHeader.jsp"%>
	<div class="container">
	    <h2>Izaberi takmičenje</h2>
	    <form action="${pageContext.request.contextPath}/adminController/izvestaji/prijavljeniTakmicari.pdf" method="get" target="_blank">
	        <div class="mb-3">
	            <select name="takmicenjeId" class="form-select">
	                <c:forEach var="t" items="${takmicenja}">
	                    <option value="${t.id}">${t.naziv}</option>
	                </c:forEach>
	            </select>
	        </div>
	        <div class="d-grid">
	            <button type="submit" class="btn btn-primary btn-block">Štampaj</button>
	        </div>
	    </form>
	</div>
</body>
</html>
