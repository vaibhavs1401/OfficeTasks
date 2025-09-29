<%-- 
    Document   : studentDetails.jsp
    Created on : 29-Sep-2025, 11:11:34 am
    Author     : hcdc
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Details</title>
</head>
<body>
<h2>Student Details</h2>
<table border="1">
    <tr><th>Roll No</th><td>${student.rollNo}</td></tr>
    <tr><th>Name</th><td>${student.name}</td></tr>
    <tr><th>Class</th><td>${student.studentClass}</td></tr>
    <tr><th>Age</th><td>${student.age}</td></tr>
    <!-- Add other fields as required -->
</table>
<a href="/admin/studentlist">Back to Student List</a>
</body>
</html>
