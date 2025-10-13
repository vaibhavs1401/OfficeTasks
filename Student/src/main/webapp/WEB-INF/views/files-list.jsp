<%-- 
    Document   : files-list
    Created on : 13-Oct-2025, 2:47:52 pm
    Author     : hcdc
--%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - File List</title>
      <link rel="stylesheet" href="<c:url value='/css/updateStudent.css' />" />
</head>
<body>

<h2 style="text-align: center;">Admin - Files List</h2>

<c:if test="${not empty success}">
    <div class="message success">${success}</div>
</c:if>

<c:if test="${not empty error}">
    <div class="message error">${error}</div>
</c:if>

<div class="filter-form">
    <form method="get" action="${pageContext.request.contextPath}/admin/files">
        <label for="status">Filter by Status:</label>
        <select id="status" name="status" onchange="this.form.submit()">
            <option value="" <c:if test="${statusFilter == 'ALL'}">selected</c:if>>All</option>
            <option value="PENDING" <c:if test="${statusFilter == 'PENDING'}">selected</c:if>>Pending</option>
            <option value="APPROVED" <c:if test="${statusFilter == 'APPROVED'}">selected</c:if>>Approved</option>
            <option value="REJECTED" <c:if test="${statusFilter == 'REJECTED'}">selected</c:if>>Rejected</option>
        </select>
    </form>
</div>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Original Filename</th>
            <th>Uploaded By</th>
            <th>Uploaded At</th>
            <th>Status</th>
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
            <td>${file.status}</td>
            <td class="actions">
                <c:choose>
                    <c:when test="${file.status == 'PENDING'}">
                        <form action="${pageContext.request.contextPath}/admin/files/approve/${file.id}" method="post" style="display:inline;">
                            <input type="hidden" name="page" value="${currentPage}"/>
                            <button type="submit">Approve</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/files/reject/${file.id}" method="post" style="display:inline;">
                            <input type="hidden" name="page" value="${currentPage}"/>
                            <button type="submit">Reject</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        No actions
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="pagination">
    <span>Page ${currentPage + 1} of ${totalPages}</span><br/>

    <c:choose>
        <c:when test="${currentPage > 0}">
            <a href="${pageContext.request.contextPath}/admin/files?status=${statusFilter == 'ALL' ? '' : statusFilter}&page=${currentPage - 1}">Previous</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">Previous</a>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${currentPage + 1 < totalPages}">
            <a href="${pageContext.request.contextPath}/admin/files?status=${statusFilter == 'ALL' ? '' : statusFilter}&page=${currentPage + 1}">Next</a>
        </c:when>
        <c:otherwise>
            <a class="disabled">Next</a>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
