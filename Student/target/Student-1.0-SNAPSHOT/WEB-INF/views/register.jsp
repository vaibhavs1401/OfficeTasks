<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Register</title></head>
<body>
<h2>Register</h2>

<c:if test="${not empty error}">
  <div style="color:red;"><c:out value="${error}"/></div>
</c:if>

<form action="<c:url value='/auth/register'/>" method="post">
  <label>Name: <input type="text" name="name" required></label><br/>
  <label>Email: <input type="email" name="email" required></label><br/>
  <label>Password: <input type="password" name="password" required></label><br/>
  <label>Class (std): <input type="number" name="std" min="1" max="12" required></label><br/>
  <label>Age: <input type="number" name="age" min="3" max="100" required></label><br/>
  <label>Roll No: <input type="text" name="rollNo" required></label><br/>
  <button type="submit">Register</button>
</form>

<p>Already registered? <a href="<c:url value='/auth/login'/>">Login</a></p>
</body>
</html>
