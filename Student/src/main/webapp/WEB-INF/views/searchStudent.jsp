<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Search Students</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Link your external CSS -->    
    <link rel="stylesheet" href="<c:url value='/css/searchStudent.css' />" />
</head>
<body>

<div class="page">
    <header class="header">
        <h1 class="title">Search Students</h1>
        <p class="subtitle">Find students by name. Partial matches are supported.</p>
    </header>

    <section class="card">
        <form class="search-form" action="${pageContext.request.contextPath}/admin/search" method="get">
            <label class="sr-only" for="name">Name</label>
            <div class="input-row">
                <input id="name" name="name" type="text" class="input"
                       placeholder="Type a name, e.g., 'Alice'"
                       value="${fn:escapeXml(name)}" />
                <button type="submit" class="btn">Search</button>
            </div>
        </form>

        <c:choose>
            <c:when test="${empty name}">
                <div class="empty muted">
                    <span class="dot"></span>
                    Type a name and press <strong>Search</strong>.
                </div>
            </c:when>

            <c:when test="${empty students}">
                <div class="empty">
                    <h3 class="empty-title">No results</h3>
                    <p class="empty-text">We couldn’t find any students for “<strong><c:out value='${name}'/></strong>”.</p>
                    <ul class="tips">
                        <li>Check spelling.</li>
                        <li>Try a shorter or partial name.</li>
                    </ul>
                </div>
            </c:when>

            <c:otherwise>
                <div class="results-head">
                    <p>Showing results for “<strong><c:out value='${name}'/></strong>”</p>
                    <span class="badge"><c:out value='${fn:length(students)}'/> found</span>
                </div>

                <div class="table-wrap">
                    <table class="table">
                        <thead>
                            <tr>
                                <th style="width:120px;">ID</th>
                                <th>Name</th>
                                <th>Standard</th>
                                <th>Age</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${students}">
                                <tr>
                                    <td><c:out value="${s.id}"/></td>
                                    <td class="name-cell"><c:out value="${s.name}"/></td>
                                    <td><c:out value="${s.standard}"/></td>
                                    <td><c:out value="${s.age}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>

    <footer class="footer muted">
        <small>Student Management • Spring MVC + JSP</small>
    </footer>
</div>

</body>
</html>
