<%-- 
    Document   : header.jsp
    Created on : 30-Sep-2025
    Updated    : 05-Oct-2025
    Description: Shared site header with navigation and conditional login/logout button.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- External CSS link -->
<link rel="stylesheet" href="<c:url value='/css/app.css'/>" />

<!-- Header -->
<div class="header">
  <div class="nav">
    <a href="<c:url value='/'/>">Home</a>
    <a href="<c:url value='/admin/studentlist'/>">All Students</a>
    <a href="<c:url value='/student/profile'/>">My Profile</a>
  </div>

  <div class="auth-btn">
    <c:choose>
      <c:when test="${pageContext.request.requestURI ne pageContext.request.contextPath.concat('/auth/login')
                      and pageContext.request.requestURI ne pageContext.request.contextPath.concat('/auth/register')}">
        <%-- Show logout button when not on login/register page --%>
        <form action="<c:url value='/auth/logout'/>" method="post" style="display:inline;">
          <button type="submit" class="btn ghost">Logout</button>
        </form>
      </c:when>
      <c:otherwise>
        <%-- Show login button on login/register pages --%>
        <a class="btn" href="<c:url value='/auth/login'/>">Login</a>
      </c:otherwise>
    </c:choose>
  </div>
</div>

<!-- Main container wrapper -->
<div class="container">
