<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<!DOCTYPE html>
<html>
<head><title>Student Home</title></head>
<body>
  <div class="card">
    <h2>Welcome to the Student Portal</h2>
    <p class="tag">JWT-secured</p>
    <div class="actions" style="margin-top:12px;">
      <a class="btn" href="<c:url value='/auth/login'/>">Login</a>
      <a class="btn ghost" href="<c:url value='/auth/register'/>">Register</a>
    </div>
  </div>
</div>
</body>
</html>
