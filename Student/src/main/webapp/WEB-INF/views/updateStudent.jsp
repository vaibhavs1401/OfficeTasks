<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Update Student</title>
  <link rel="stylesheet" href="<c:url value='/css/updateStudent.css' />" />
</head>
<body>
  <main class="main-content container">
    <div class="card">
      <h2>Update Student Details</h2>

      <form:form modelAttribute="student" method="post" action="<c:url value='/admin/student/update'/>" class="student-form">
        <form:hidden path="id" />
        
        <div class="form-row">
          <label for="rollNo">Roll No</label>
          <form:input path="rollNo" id="rollNo" readonly="true" />
        </div>

        <div class="form-row">
          <label for="name">Name</label>
          <form:input path="name" id="name" />
        </div>

        <div class="form-row">
          <label for="age">Age</label>
          <form:input path="age" id="age" type="number" />
        </div>

        <div class="form-row">
          <label for="standard">Class (Standard)</label>
          <form:input path="standard" id="standard" type="number" />
        </div>

        <div class="form-row">
          <label for="accountId">User Account ID</label>
          <form:input path="account.id" id="accountId" type="number" />
        </div>

        <div class="actions">
          <button type="submit" class="btn">Update</button>
          <a class="btn ghost" href="<c:url value='/admin/studentlist' />">Back to List</a>
        </div>
      </form:form>
    </div>
  </main>
</body>
</html>
