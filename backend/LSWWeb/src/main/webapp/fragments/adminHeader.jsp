<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header style="background-color:#1f1f1f; padding:1rem 2rem; display:flex; justify-content:space-between; align-items:center;">
    <h1 style="font-size:1.5rem; color:#f5f5f5; margin:0;">
        <a href="${pageContext.request.contextPath}/adminController/home" style="color:white; text-decoration:none;">
            Admin panel
        </a>
    </h1>
    <div class="auth-links">
        <a href="${pageContext.request.contextPath}/accController/logout" class="ms-2">Odjavi se</a>
    </div>
</header>
