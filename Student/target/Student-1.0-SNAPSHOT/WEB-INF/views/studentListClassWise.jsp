<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Student List - Class Wise</title></head>
<body>
<h2>Students of Class ${param['class']}</h2>
<table border="1">
  <thead>
  <tr>
    <th>Roll No</th>
    <th>Name</th>
    <th>Age</th>
    <th>Details</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="student" items="${list}">
    <tr>
      <td>${student.rollNo}</td>
      <td>${student.name}</td>
      <td>${student.age}</td>
      <td>
        <a href="<c:url value='/admin/student'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">View</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="<c:url value='/admin/studentlist'/>">Back to All Students</a>
</body>
</html>
