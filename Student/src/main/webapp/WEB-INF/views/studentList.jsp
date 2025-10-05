<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<html>
<head><title>Student List</title></head>
<body>
  <h2>All Students</h2>

  <div class="card">
    <div class="actions" style="justify-content:space-between;">
      <a href="<c:url value='/student/add' />" class="btn">Add New Student</a>

      <form action="<c:url value='/admin/classwise'/>" method="get" class="actions" style="gap:8px;">
        <label for="std" style="margin:0;">Filter by Class</label>
        <input type="number" name="std" id="std" min="1" max="12" required />
        <button type="submit" class="btn ghost">Filter</button>
      </form>
    </div>

    <table style="margin-top:12px;">
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
            <a class="btn ghost" href="<c:url value='/admin/student'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">View</a>
          </td>
          <td class="actions">
            <a class="btn ghost" href="<c:url value='/student/update'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">Update</a>
            <form action="<c:url value='/student/delete' />" method="post" style="display:inline;">
              <input type="hidden" name="rollNo" value="${student.rollNo}" />
              <button class="btn" style="background:#ef4444" type="submit"
                      onclick="return confirm('Delete ${student.name}?');">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
