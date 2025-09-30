<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Students in Class ${classStd}</title>
</head>
<body>
    <h2>Students in Class ${classStd}</h2>

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
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.rollNo}</td>
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/student?rollNo=${student.rollNo}">View</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p><a href="${pageContext.request.contextPath}/admin/studentlist">Back to Student List</a></p>
</body>
</html>
