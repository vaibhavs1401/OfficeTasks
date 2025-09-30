<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add New Student</title>
</head>
<body>
    <h2>Add New Student</h2>

    <form:form modelAttribute="student" method="post" action="${pageContext.request.contextPath}/student/add">
        <table>
            <tr>
                <td>Roll No:</td>
                <td><form:input path="rollNo" /></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td>User Role:</td>
                <td><form:input path="userRole" /></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><form:input path="age" /></td>
            </tr>
            <tr>
                <td>Class (std):</td>
                <td><form:input path="std" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Add Student" /></td>
            </tr>
        </table>
    </form:form>

    <p><a href="${pageContext.request.contextPath}/admin/studentList">Back to List</a></p>
</body>
</html>


