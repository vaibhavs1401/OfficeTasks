<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Student Details</title></head>
<body>
<h2>Student Details</h2>
<table border="1">
  <tr><th>Roll No</th><td>${student.rollNo}</td></tr>
  <tr><th>Name</th><td>${student.name}</td></tr>
  <tr><th>Class</th><td>${student.std}</td></tr>
  <tr><th>Age</th><td>${student.age}</td></tr>
</table>
<a href="<c:url value='/admin/studentlist'/>">Back to Student List</a>
</body>
</html>
