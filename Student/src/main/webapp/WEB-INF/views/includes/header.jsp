<%-- 
    Document   : header.jsp
    Created on : 30-Sep-2025, 10:03:40 am
    Author     : hcdc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String path = request.getRequestURI();
    if (!(path.endsWith("/login") || path.endsWith("/register"))) {
%>
    <form action="/Student/auth/logout" method="post" style="display:inline;">
        <button type="submit">Logout</button>
    </form>
<% 
    } 
%>
