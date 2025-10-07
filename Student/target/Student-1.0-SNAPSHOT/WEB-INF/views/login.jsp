<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Login</title>
  <link rel="stylesheet" href="<c:url value='/css/app.css' />" />
</head>
<body>
  <div class="card" role="main" aria-label="Login form">
    <h2>Login</h2>

    <c:if test="${not empty param.registered}">
      <div class="alert ok" role="alert">Registration successful. Please login.</div>
    </c:if>
    <c:if test="${not empty param.logout}">
      <div class="alert ok" role="alert">You have been logged out.</div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert err" role="alert"><c:out value="${error}"/></div>
    </c:if>

    <form action="<c:url value='/auth/login'/>" method="post" style="margin-top:12px;">
      <div class="form-row">
        <label for="email">Email</label>
        <input id="email" type="email" name="email" required />
      </div>
      <div class="form-row">
        <label for="password">Password</label>
        <input id="password" type="password" name="password" required />
      </div>
      <div class="actions">
        <button type="submit" class="btn">Login</button>
        <a class="btn ghost" href="<c:url value='/auth/register'/>">Register</a>
      </div>
    </form>
  </div>
</body>
</html>
