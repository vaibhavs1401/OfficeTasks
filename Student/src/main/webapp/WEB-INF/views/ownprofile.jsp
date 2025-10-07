<%-- 
    Document   : ownprofile
    Created on : 07-Oct-2025, 3:48:37 pm
    Author     : hcdc
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Student Details</title>
  <link rel="stylesheet" href="<c:url value='/css/studentDetails.css' />" />
</head>
<body>
  <main class="main-content container">
    <div class="card">
      <h2>Student Details</h2>

      <table class="details-table">
        <tr>
          <th>Roll No</th>
          <td>${student.rollNo}</td>
        </tr>
        <tr>
          <th>Name</th>
          <td>${student.name}</td>
        </tr>
        <tr>
          <th>Class</th>
          <td>${student.standard}</td>
        </tr>
        <tr>
          <th>Age</th>
          <td>${student.age}</td>
        </tr>
        <tr>
          <th>Linked Email</th>
          <td>${student.account.email}</td>
        </tr>
      </table>

      <div class="actions">
        <a class="btn ghost" href="<c:url value='/admin/studentlist' />">Back to List</a>
        <a class="btn" href="<c:url value='/admin/student/update'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">Edit</a>
      </div>
    </div>
  </main>
</body>
</html>



