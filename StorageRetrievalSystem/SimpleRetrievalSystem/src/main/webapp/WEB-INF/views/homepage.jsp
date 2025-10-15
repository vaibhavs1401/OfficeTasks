<%-- 
    Document   : homepage.jsp
    Created on : 15-Oct-2025, 11:03:35 am
    Author     : hcdc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document Info Hub</title>
    <link rel="stylesheet" type="text/css" href="css/homepage.css">
</head>
<body>

    <header>
        <h1>Welcome to Document Info Hub</h1>
        <p>Your central place for document types and formats</p>
    </header>

    <nav>
        <a href="#">Home</a>
        <a href="#">About</a>
        <a href="#">Documents</a>
        <a href="#">Contact</a>
    </nav>

    <div class="container">
        <section class="about">
            <h2>About This Site</h2>
            <p>This website provides information about different types of document files such as PDF, Word, Excel, and more. Learn about their uses, formats, and best practices for handling them.</p>
        </section>

        <section class="documents">
            <h2>Document Categories</h2>
            <div class="doc-grid">
                <div class="doc-card">
                    <h3>PDF Files</h3>
                    <p>Learn about Portable Document Format and how to use it.</p>
                </div>
                <div class="doc-card">
                    <h3>Word Documents</h3>
                    <p>Information on DOC and DOCX files, features, and compatibility.</p>
                </div>
                <div class="doc-card">
                    <h3>Excel Sheets</h3>
                    <p>Explore XLS and XLSX files used for spreadsheets and data.</p>
                </div>
                <div class="doc-card">
                    <h3>Text Files</h3>
                    <p>Plain text files (TXT), usage, and formatting tips.</p>
                </div>
            </div>
        </section>
    </div>

    <footer>
        &copy; 2025 Document Info Hub. All rights reserved @Vaibhav Shinde.
    </footer>
    
     <!--Cookie Banner Included -->
    <jsp:include page="cookieBanner.jsp" />


</body>
</html>


