<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>

<c:if test="${not empty param.registered}">
  <div style="color:green;">Registration successful. Please login.</div>
</c:if>
<c:if test="${not empty param.logout}">
  <div style="color:green;">You have been logged out.</div>
</c:if>
<c:if test="${not empty error}">
  <div style="color:red;"><c:out value="${error}"/></div>
</c:if>

<form action="<c:url value='/auth/login'/>" method="post">
  <label>Email: <input type="email" name="email" required></label><br/>
  <label>Password: <input type="password" name="password" required></label><br/>
  <button type="submit">Login</button>
</form>

<p>New user? <a href="<c:url value='/auth/register'/>">Register</a></p>
</body>
</html>
