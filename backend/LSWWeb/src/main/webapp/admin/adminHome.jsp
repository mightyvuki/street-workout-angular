<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="sr">
<head>
<meta charset="UTF-8">
<title>Admin panel</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
    text-align: center
}

.card {
    background-color: #2a2a2a;
    border: none;
    border-radius: 10px;
    color: #f1f1f1;
    margin: 1em;
    padding: 2.5em;
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

    <%@ include file="/fragments/adminHeader.jsp"%>

    <div class="container">
        <h2 class="section-title">Administracija takmičenja</h2>

        <div class="row justify-content-center">
            <div class="col-md-4 mb-4">
                <div class="card p-4 text-center">
                    <h5>Takmičenje</h5>
                    <p>Dodaj, izmeni ili obriši takmičenja.</p>
                    <a href="${pageContext.request.contextPath}/adminController/takmicenja" class="btn btn-custom">Otvori</a>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card p-4 text-center">
                    <h5>Prijave</h5>
                    <p>Pregled i upravljanje prijavama po takmičenju.</p>
                    <a href="${pageContext.request.contextPath}/adminController/prijave" class="btn btn-custom">Otvori</a>
                </div>
            </div>
        </div>

        <div class="row mt-4 justify-content-center">
            <div class="col-md-4 mb-4">
                <div class="card p-4 text-center">
                    <h5>Lista prijavljenih</h5>
                    <p>Broj prijavljenih takmičara i lista svih takmičara zajedno sa datumom prijave.</p>
                    <a href="${pageContext.request.contextPath}/adminController/izvestaji/listaTakmicara" class="btn btn-custom">Otvori</a>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card p-4 text-center">
                    <h5>Izveštaj proseka</h5>
                    <p>Prosečan broj pravilno urađenih ponavljanja po disciplinama.</p>
                    <a href="${pageContext.request.contextPath}/adminController/izvestaji/prosek" class="btn btn-custom">Otvori</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
