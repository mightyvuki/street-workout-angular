<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="sr">
<head>
    <meta charset="UTF-8">
    <title>Registracija</title>
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

        .register-box {
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
        
        .error {
        	color: red;
        }
    </style>
</head>
<body>
    <div class="register-box">
        <h2 class="text-center mb-4">Registracija</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form:form modelAttribute="korisnik"
                   action="${pageContext.request.contextPath}/accController/register"
                   method="post">

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="ime">Ime:</label>
                    <form:input path="ime" id="ime" class="form-control"/>
                    <form:errors path="ime" cssClass="error" />
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="prezime">Prezime:</label>
                    <form:input path="prezime" id="prezime" class="form-control"/>
                    <form:errors path="prezime" cssClass="error" />
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label" for="username">Korisničko ime:</label>
                <form:input path="username" id="username" class="form-control"/>
                <form:errors path="username" cssClass="error" />
            </div>

            <div class="mb-3">
                <label class="form-label" for="email">Email:</label>
                <form:input path="email" id="email" type="email" class="form-control"/>
                <form:errors path="email" cssClass="error" />
            </div>

            <div class="mb-3">
                <label class="form-label" for="password">Lozinka:</label>
                <form:password path="password" id="password" class="form-control"/>
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="mb-3">
                <label class="form-label" for="pol">Pol:</label>
                <form:select path="pol" id="pol" class="form-select">
                    <form:option value="" label="Izaberi" />
                    <form:option value="muško" label="Muško" />
                    <form:option value="žensko" label="Žensko" />
                </form:select>
                <form:errors path="pol" cssClass="error" />
            </div>

            <div class="mb-3">
                <label class="form-label" for="datumRodjenja">Datum rođenja:</label>
                <form:input path="datumRodjenja" id="datumRodjenja" type="date" class="form-control"/>
                <form:errors path="datumRodjenja" cssClass="error" />
            </div>

            <button type="submit" class="btn btn-custom w-100">Registruj se</button>
        </form:form>

        <div class="mt-3 text-center">
            Već imaš nalog? <a href="${pageContext.request.contextPath}/accController/login">Prijavi se</a><br>
            <a href="${pageContext.request.contextPath}/">Povratak na početnu</a>
        </div>
    </div>
</body>
</html>
