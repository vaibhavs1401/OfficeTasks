<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<html>
<head><title>Login</title></head>
<body>
  <div class="card">
    <h2>Login</h2>

    <c:if test="${not empty param.registered}">
      <div class="alert ok">Registration successful. Please login.</div>
    </c:if>
    <c:if test="${not empty param.logout}">
      <div class="alert ok">You have been logged out.</div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert err"><c:out value="${error}"/></div>
    </c:if>

    <form action="<c:url value='/auth/login'/>" method="post" style="margin-top:12px;">
      <div class="form-row">
        <label>Email</label>
        <input type="email" name="email" required/>
      </div>
      <div class="form-row">
        <label>Password</label>
        <input type="password" name="password" required/>
      </div>
      <div class="actions">
        <button type="submit" class="btn">Login</button>
        <a class="btn ghost" href="<c:url value='/auth/register'/>">Register</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
