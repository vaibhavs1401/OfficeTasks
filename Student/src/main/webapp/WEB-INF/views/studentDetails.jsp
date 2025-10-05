<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<html>
<head><title>Student Details</title></head>
<body>
  <div class="card">
    <h2>Student Details</h2>
    <table>
      <tr><th>Roll No</th><td>${student.rollNo}</td></tr>
      <tr><th>Name</th><td>${student.name}</td></tr>
      <tr><th>Class</th><td>${student.standard}</td></tr>
      <tr><th>Age</th><td>${student.age}</td></tr>
    </table>
    <div class="actions" style="margin-top:12px;">
      <a class="btn ghost" href="<c:url value='/admin/studentlist'/>">Back to Student List</a>
    </div>
  </div>
</div>
</body>
</html>
