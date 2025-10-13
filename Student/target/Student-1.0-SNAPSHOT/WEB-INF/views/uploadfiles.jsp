<%-- 
    Document   : uploadfiles
    Created on : 13-Oct-2025, 3:06:25 pm
    Author     : hcdc
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="includes/studentheader.jsp" />s

<html>
<head>
    <title>Upload Files</title>
</head>
<body>
<h2>Upload Files for Student: ${student.name} (Roll No: ${student.rollNo})</h2>

<!-- Show success or error flash messages -->
<c:if test="${not empty success}">
    <p style="color:green;">${success}</p>
</c:if>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<!-- File upload form -->
<form method="post" action="${pageContext.request.contextPath}/student/uploadfiles" enctype="multipart/form-data">
    <label for="files">Select files to upload:</label><br/>
    <input type="file" id="files" name="files" multiple required/><br/><br/>
    <button type="submit">Upload</button>
</form>

</body>
</html>

