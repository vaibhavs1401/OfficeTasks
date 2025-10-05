<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html>
<head><title>Add New Student</title></head>
<body>
  <div class="card">
    <h2>Add New Student</h2>

    <form:form modelAttribute="student" method="post" action="<c:url value='/student/add'/>" style="margin-top:12px;">
      <div class="form-row">
        <label>Roll No</label>
        <form:input path="rollNo"/>
      </div>
      <div class="form-row">
        <label>Name</label>
        <form:input path="name"/>
      </div>
      <div class="form-row">
        <label>Class (standard)</label>
        <form:input path="standard" type="number"/>
      </div>
      <div class="form-row">
        <label>Age</label>
        <form:input path="age" type="number"/>
      </div>
      <div class="form-row">
        <label>Linked User Account ID</label>
        <!-- Bind nested property student.account.id -->
        <form:input path="account.id" type="number"/>
      </div>
      <div class="actions">
        <button type="submit" class="btn">Add Student</button>
        <a class="btn ghost" href="<c:url value='/admin/studentlist'/>">Back to List</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>
