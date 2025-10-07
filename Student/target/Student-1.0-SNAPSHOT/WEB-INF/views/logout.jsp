<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Logout</title>
  <link rel="stylesheet" href="<c:url value='/css/app.css' />" />
</head>
<body>
  <div class="card" role="main" aria-label="Logout confirmation">
    <h2>Thank you for using our app!</h2>
    <p><a class="btn" href="<c:url value='/auth/login'/>">Login again</a></p>
  </div>
</body>
</html>
