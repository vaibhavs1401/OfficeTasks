<%-- 
    Document   : studentList.jsp
    Created on : 29-Sep-2025, 11:12:09 am
    Author     : hcdc
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h2>All Students</h2>
<table border="1">
    <thead>
    <tr>
        <th>Roll No</th>
        <th>Name</th>
        <th>Class</th>
        <th>Age</th>
        <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${list}">
        <tr>
            <td>${student.rollNo}</td>
            <td>${student.name}</td>
            <td>${student.studentClass}</td>
            <td>${student.age}</td>
            <td><a href="/admin/student?rollNo=${student.rollNo}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

