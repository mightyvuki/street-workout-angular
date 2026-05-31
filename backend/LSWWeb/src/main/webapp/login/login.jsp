<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="sr">
<head>
    <meta charset="UTF-8">
    <title>Prijava | Street Workout Lovćenac</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
        .login-box {
            background-color: #2a2a2a;
            padding: 2rem 3rem;
            border-radius: 10px;
            box-shadow: 0 0 10px #000;
            width: 100%;
            max-width: 400px;
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
    </style>
</head>
<body>
<div class="login-box text-center">
    <h2 class="mb-4">Prijava</h2>
    <c:if test="${not empty success}">
    	<div class="alert alert-success">${success}</div>
	</c:if>
	<c:if test="${not empty error}">
    	<div class="alert alert-danger">${error}</div>
	</c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <input type="text" name="username" class="form-control" placeholder="Korisničko ime" required>
        </div>
        <div class="mb-3">
            <input type="password" name="password" class="form-control" placeholder="Lozinka" required>
        </div>
        <button type="submit" class="btn btn-custom w-100">Prijavi se</button>
    </form>
    <div class="mt-3">
        Nemaš nalog? <a href="/LSW/accController/register">Registruj se</a><br>
        <a href="/LSW">Povratak na početnu</a>
    </div>
</div>
</body>
</html>
