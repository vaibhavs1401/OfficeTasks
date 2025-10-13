<%-- 
    Document   : myfiles.jsp
    Created on : 13-Oct-2025, 2:54:26 pm
    Author     : hcdc
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="includes/studentheader.jsp" />

<html>
<head>
    <title>My Uploaded Files</title>
      <link rel="stylesheet" href="<c:url value='/css/updateStudent.css' />" />
</head>
<body>
<h2>My Uploaded Files and Status</h2>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<table border="1" cellpadding="5" cellspacing="0">
    <thead>
        <tr>
            <th>File ID</th>
            <th>Original Filename</th>
            <th>Upload Date</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="file" items="${files}">
        <tr>
            <td>${file.id}</td>
            <td>${file.originalFileName}</td>
            <td><fmt:formatDate value="${file.uploadedAt}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${file.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

