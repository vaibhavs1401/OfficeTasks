<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Student List</title></head>
<body>
<h2>All Students</h2>
<table border="1">
  <thead>
  <tr>
    <th>Roll No</th>
    <th>Name</th>
    <th>Class</th>
    <th>Age</th>
    <th>Details</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="student" items="${list}">
    <tr>
      <td>${student.rollNo}</td>
      <td>${student.name}</td>
      <td>${student.std}</td>
      <td>${student.age}</td>
      <td>
        <a href="<c:url value='/admin/student'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">View</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
