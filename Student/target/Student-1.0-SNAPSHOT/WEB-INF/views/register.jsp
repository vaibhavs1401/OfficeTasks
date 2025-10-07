<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Register</title>
  <link rel="stylesheet" href="<c:url value='/css/app.css' />" />
</head>
<body>
  <div class="card" role="main" aria-label="Register form">
    <h2>Register</h2>

    <c:if test="${not empty error}">
      <div class="alert err" role="alert"><c:out value="${error}" /></div>
    </c:if>

    <form action="<c:url value='/auth/register'/>" method="post" style="margin-top: 12px;">
      <div class="form-row">
        <label for="name">Name</label>
        <input id="name" type="text" name="name" required />
      </div>
      <div class="form-row">
        <label for="email">Email</label>
        <input id="email" type="email" name="email" required />
      </div>
      <div class="form-row">
        <label for="password">Password</label>
        <input id="password" type="password" name="password" required />
      </div>
      <div class="form-row">
        <label for="standard">Class (standard)</label>
        <input id="standard" type="number" name="standard" min="1" max="12" required />
      </div>
      <div class="form-row">
        <label for="age">Age</label>
        <input id="age" type="number" name="age" min="3" max="100" required />
      </div>
      <div class="form-row">
        <label for="rollNo">Roll No</label>
        <input id="rollNo" type="text" name="rollNo" required />
      </div>
      <div class="actions">
        <button type="submit" class="btn">Create account</button>
        <a class="btn ghost" href="<c:url value='/auth/login' />">Back to login</a>
      </div>
    </form>
  </div>
</body>
</html>
