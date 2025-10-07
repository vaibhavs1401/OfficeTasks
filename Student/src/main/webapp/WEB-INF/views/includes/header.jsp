<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="<c:url value='/css/header.css'/>" />

<header class="site-header">
  <nav class="nav-bar container">
    <div class="nav-links">
      <a href="<c:url value='/'/>" class="nav-link">Home</a>
      <a href="<c:url value='/admin/studentlist'/>" class="nav-link">All Students</a>
      <a href="<c:url value='/student/profile'/>" class="nav-link">My Profile</a>
    </div>

    <div class="auth-section">
      <c:choose>
        <c:when test="${pageContext.request.requestURI ne pageContext.request.contextPath.concat('/auth/login')
                        and pageContext.request.requestURI ne pageContext.request.contextPath.concat('/auth/register')}">
          <!-- Show logout button when NOT on login/register page -->
          <form action="<c:url value='/auth/logout'/>" method="post" class="logout-form">
            <button type="submit" class="btn btn-ghost">Logout</button>
          </form>
        </c:when>
        <c:otherwise>
          <!-- Show login button on login/register pages -->
          <a href="<c:url value='/auth/login'/>" class="btn btn-primary">Login</a>
        </c:otherwise>
      </c:choose>
    </div>
  </nav>
</header>

<div class="container">
