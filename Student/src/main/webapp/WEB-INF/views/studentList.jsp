<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Student List</title>
  <link rel="stylesheet" href="<c:url value='/css/studentList.css' />" />
</head>
<body>
  <main class="main-content container">
    <h2 class="page-title">All Students</h2>

    <div class="toolbar">
      <a href="<c:url value='/admin/student/add' />" class="btn btn-primary">Add New Student</a>

      <form action="<c:url value='/admin/classwise'/>" method="get" class="filter-form">
        <label for="std">Filter by Class:</label>
        <input type="number" name="std" id="std" min="1" max="12" required />
        <button type="submit" class="btn btn-ghost">Filter</button>
      </form>
    </div>

    <table class="student-table">
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
          <td>${student.standard}</td>
          <td>${student.age}</td>
          <td>
            <a class="btn btn-ghost" href="<c:url value='/admin/student'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">View</a>
          </td>
          <td class="actions">
            <a class="btn btn-ghost" href="<c:url value='/admin/student/update'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">Update</a>
            <form action="<c:url value='/admin/student/delete' />" method="post" class="inline-form" 
                  onsubmit="return confirm('Delete ${student.name}?');">
              <input type="hidden" name="rollNo" value="${student.rollNo}" />
              <button class="btn btn-danger" type="submit">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </main>
</body>
</html>
