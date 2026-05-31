<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Moji rezultati</title>
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
    <%@ include file="/fragments/header.jsp" %>

    <div class="container">
        <h2 class="section-title">Moji rezultati</h2>

        <c:if test="${empty rezultati}">
            <p>Niste učestvovali ni na jednom takmičenju.</p>
        </c:if>

        <c:if test="${not empty rezultati}">
            <table class="table table-bordered table-dark text-center">
                <thead>
                    <tr>
                        <th>Naziv takmičenja</th>
                        <th>Naziv discipline</th>
                        <th>Broj ponavljanja</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${rezultati}">
					    <tr>
					        <td>${r.takmicenje.naziv}</td>
					        <td>${r.disciplina.naziv}</td>
					        <td>${r.rezultat}</td>
					    </tr>
					</c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
