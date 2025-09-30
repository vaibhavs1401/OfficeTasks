<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />
<html>
<head><title>Student List</title></head>
<body>
<h2>All Students</h2>

<!-- Add New Student Button -->
<p>
    <a href="<c:url value='/student/add' />">
        <button type="button">Add New Student</button>
    </a>
</p>
<form action="${pageContext.request.contextPath}/admin/classwise" method="get">
    <label for="std">Filter by Class:</label>
    <input type="number" name="std" id="std" min="1" max="12" required />
    <button type="submit">Filter</button>
</form>

<table border="1">
  <thead>
  <tr>
    <th>Roll No</th>
    <th>Name</th>
    <th>Class</th>
    <th>Age</th>
    <th>Details</th>
    <th>Actions</th>
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
      <td>
        <!-- Update Button -->
        <a href="<c:url value='/student/update'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">
            <button type="button">Update</button>
        </a>
        <form action="<c:url value='/student/delete' />" method="post" style="display:inline;">
            <input type="hidden" name="rollNo" value="${student.rollNo}" />
            <button type="submit" onclick="return confirm('Are you sure you want to delete this student?');">Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
