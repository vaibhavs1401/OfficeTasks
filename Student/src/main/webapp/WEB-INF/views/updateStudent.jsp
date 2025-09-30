<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html>
<head><title>Update Student</title></head>
<body>
<h2>Update Student Details</h2>

<form:form modelAttribute="student" method="post" action="${pageContext.request.contextPath}/student/update">
    <table>
        <!-- Hidden ID field to bind the primary key -->
        <form:hidden path="id" />
        <tr><td>Roll No:</td><td><form:input path="rollNo" readonly="true"/></td></tr>
        <tr><td>Name:</td><td><form:input path="name"/></td></tr>
        <tr><td>Email:</td><td><form:input path="email"/></td></tr>
        <tr><td>Age:</td><td><form:input path="age"/></td></tr>
        <tr><td>Class (std):</td><td><form:input path="std"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="Update"/></td></tr>
    </table>
</form:form>

<p><a href="${pageContext.request.contextPath}/admin/studentList">Back to List</a></p>

</body>
</html>
