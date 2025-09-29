<%-- 
    Document   : studentListClassWise
    Created on : 29-Sep-2025, 11:12:45 am
    Author     : hcdc
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student List - Class Wise</title>
</head>
<body>
<h2>Students of Class ${param.class}</h2>
<table border="1">
    <thead>
    <tr>
        <th>Roll No</th>
        <th>Name</th>
        <th>Age</th>
        <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${list}">
        <tr>
            <td>${student.rollNo}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td><a href="/admin/student?rollNo=${student.rollNo}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/admin/studentlist">Back to All Students</a>
</body>
</html>

