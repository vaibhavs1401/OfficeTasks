<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Add Student & User Account</title>
  <link rel="stylesheet" href="<c:url value='/css/studentForm.css' />" />
</head>
<body>
  <main class="main-content container">
    <div class="card">
      <h2>Add New Student & User Account</h2>

      <c:if test="${not empty error}">
        <div class="alert err"><c:out value="${error}" /></div>
      </c:if>

      <c:url var="addStudentUrl" value="/admin/student/addWithAccount" />
      <form:form modelAttribute="student" method="post" action="${addStudentUrl}">

        <!-- User Account Fields -->
        <fieldset>
          <legend>User Account Details</legend>

          <div class="form-row">
            <form:label path="account.email">Email</form:label>
            <form:input path="account.email" type="email" required="true" />
          </div>

          <div class="form-row">
            <form:label path="account.password">Password</form:label>
            <form:password path="account.password" required="true" />
          </div>
        </fieldset>

        <!-- Student Fields -->
        <fieldset>
          <legend>Student Details</legend>

          <div class="form-row">
            <form:label path="rollNo">Roll No</form:label>
            <form:input path="rollNo" required="true" />
          </div>

          <div class="form-row">
            <form:label path="name">Name</form:label>
            <form:input path="name" required="true" />
          </div>

          <div class="form-row">
            <form:label path="standard">Class (standard)</form:label>
            <form:input path="standard" type="number" min="1" max="12" required="true" />
          </div>

          <div class="form-row">
            <form:label path="age">Age</form:label>
            <form:input path="age" type="number" min="3" max="100" required="true" />
          </div>
        </fieldset>

        <div class="actions">
          <button type="submit" class="btn">Add Student with Account</button>
          <a class="btn ghost" href="<c:url value='/admin/studentlist' />">Back to List</a>
        </div>

      </form:form>
    </div>
  </main>
</body>
</html>
