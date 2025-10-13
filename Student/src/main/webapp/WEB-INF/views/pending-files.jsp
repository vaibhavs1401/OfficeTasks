<%-- 
    Document   : pending-files
    Created on : 13-Oct-2025, 2:46:06 pm
    Author     : hcdc
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pending File Approvals</title>
    <style>
          <link rel="stylesheet" href="<c:url value='/css/updateStudent.css' />" />
</head>
<body>

<h2 style="text-align: center;">Pending File Approvals</h2>

<c:if test="${not empty success}">
    <div class="message success">${success}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="message error">${error}</div>
</c:if>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Original Filename</th>
            <th>Uploaded By</th>
            <th>Uploaded At</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="file" items="${files}">
        <tr>
            <td>${file.id}</td>
            <td>${file.originalFileName}</td>
            <td>${file.user.email}</td>
            <td><fmt:formatDate value="${file.uploadedAt}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td class="actions">
                <form action="${pageContext.request.contextPath}/admin/files/approve/${file.id}" method="post">
                    <button type="submit">Approve</button>
                </form>
                <form action="${pageContext.request.contextPath}/admin/files/reject/${file.id}" method="post">
                    <button type="submit">Reject</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

