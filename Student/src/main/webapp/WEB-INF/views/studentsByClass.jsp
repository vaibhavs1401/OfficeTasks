<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />
<!DOCTYPE html>
<html>
<head><title>Students in Class ${classStd}</title></head>
<body>
  <div class="card">
    <h2>Students in Class ${classStd}</h2>

    <table>
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
              <a class="btn ghost" href="<c:url value='/admin/student'><c:param name='rollNo' value='${student.rollNo}'/></c:url>">View</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="actions" style="margin-top:12px;">
      <a class="btn ghost" href="<c:url value='/admin/studentlist'/>">Back to Student List</a>
    </div>
  </div>
</div>
</body>
</html>
