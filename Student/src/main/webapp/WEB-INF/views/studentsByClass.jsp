<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Students in Class ${classStd}</title>
  <link rel="stylesheet" href="<c:url value='/css/studentList.css' />" />
</head>
<body>
  <main class="main-content container">
    <h2 class="page-title">Students in Class ${classStd}</h2>

    <table class="student-table">
      <thead>
        <tr>
          <th>Roll No</th>
          <th>Name</th>
          <th>Age</th>
          <th>Details</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="student" items="${students}">
          <tr>
            <td>${student.rollNo}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>
              <a class="btn btn-ghost" href="<c:url value='/admin/student'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">View</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="actions" style="margin-top: 20px;">
      <a class="btn btn-ghost" href="<c:url value='/admin/studentlist'/>">Back to Full Student List</a>
    </div>
  </main>
</body>
</html>


