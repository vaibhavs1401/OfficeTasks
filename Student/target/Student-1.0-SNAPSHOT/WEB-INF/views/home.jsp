<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Student Home</title>
  <link rel="stylesheet" href="<c:url value='/css/app.css' />" />
</head>
<body>
  <div class="card" role="main" aria-label="Student Portal Home">
    <h2>Welcome to the Student Portal</h2>
    <p class="tag" aria-label="Security status">JWT-secured</p>
    <div class="actions">
      <a class="btn" href="<c:url value='/auth/login' />">Login</a>
      <a class="btn ghost" href="<c:url value='/auth/register' />">Register</a>
    </div>
  </div>
</body>
</html>
