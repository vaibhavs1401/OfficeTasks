<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html>
<head><title>Update Student</title></head>
<body>
  <div class="card">
    <h2>Update Student Details</h2>

    <form:form modelAttribute="student" method="post" action="<c:url value='/student/update'/>" style="margin-top:12px;">
      <form:hidden path="id" />
      <div class="form-row"><label>Roll No</label><form:input path="rollNo" readonly="true"/></div>
      <div class="form-row"><label>Name</label><form:input path="name"/></div>
      <div class="form-row"><label>Age</label><form:input path="age" type="number"/></div>
      <div class="form-row"><label>Class (standard)</label><form:input path="standard" type="number"/></div>

      <!-- (Optional) allow re-linking account by id if your service supports -->
      <div class="form-row"><label>User Account ID</label><form:input path="account.id" type="number"/></div>

      <div class="actions">
        <button type="submit" class="btn">Update</button>
        <a class="btn ghost" href="<c:url value='/admin/studentlist'/>">Back to List</a>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>
