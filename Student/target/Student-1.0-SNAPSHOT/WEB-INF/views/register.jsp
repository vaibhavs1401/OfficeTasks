<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<html>
<head><title>Register</title></head>
<body>
  <div class="card">
    <h2>Register</h2>

    <c:if test="${not empty error}">
      <div class="alert err"><c:out value="${error}"/></div>
    </c:if>

    <form action="<c:url value='/auth/register'/>" method="post" style="margin-top:12px;">
      <div class="form-row">
        <label>Name</label>
        <input type="text" name="name" required/>
      </div>
      <div class="form-row">
        <label>Email</label>
        <input type="email" name="email" required/>
      </div>
      <div class="form-row">
        <label>Password</label>
        <input type="password" name="password" required/>
      </div>
      <div class="form-row">
        <label>Class (standard)</label>
        <input type="number" name="standard" min="1" max="12" required/>
      </div>
      <div class="form-row">
        <label>Age</label>
        <input type="number" name="age" min="3" max="100" required/>
      </div>
      <div class="form-row">
        <label>Roll No</label>
        <input type="text" name="rollNo" required/>
      </div>
      <div class="actions">
        <button type="submit" class="btn">Create account</button>
        <a class="btn ghost" href="<c:url value='/auth/login'/>">Back to login</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
