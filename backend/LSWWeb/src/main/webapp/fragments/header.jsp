<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header style="background-color:#1f1f1f; padding:1rem 2rem; display:flex; justify-content:space-between; align-items:center;">
    <h1 style="font-size:1.5rem; color:#f5f5f5; margin:0;"><a href="${pageContext.request.contextPath}/" style="color: white; text-decoration: none">Street Workout Lovćenac</a></h1>
    <c:choose>
        <c:when test="${empty ulogovan}">
            <div class="auth-links">
                <a href="${pageContext.request.contextPath}/accController/login" class="btn btn-sm btn-custom">Prijava</a>
                <a href="${pageContext.request.contextPath}/accController/register" class="ms-2">Registracija</a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="auth-links">
                <a href="${pageContext.request.contextPath}/userController/upcoming" class="btn btn-sm btn-custom">Sledeća takmičenja</a>
                <a href="${pageContext.request.contextPath}/userController/results" class="btn btn-sm btn-custom">Moji rezultati</a>
                <a href="${pageContext.request.contextPath}/userController/editProfile" class="btn btn-sm btn-custom">Izmeni profil</a>
                <a href="${pageContext.request.contextPath}/accController/logout" class="ms-2">Odjavi se</a>
            </div>
        </c:otherwise>
    </c:choose>
</header>