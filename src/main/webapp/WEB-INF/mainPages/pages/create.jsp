<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 17.04.2025
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Sharrie</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/segments/sidebarStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/basicStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/createStyles.css">

    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf" %>
            <main>

                <h1>Create new post</h1>

                <form class="upload-form" action="${pageContext.request.contextPath}/create" method="POST"
                      enctype="multipart/form-data">
                    <label for="fileUpload">Wybierz zdjęcie:</label>
                    <input type="file" id="fileUpload" name="file" accept="image/*">
                    <img id="preview" style="display:none; max-width: 100%; margin-top: 1rem;"/>
                    <button type="submit">Prześlij</button>
                </form>

                <div class="post-info">
                    <form action="${pageContext.request.contextPath}/addPost" method="POST" enctype="multipart/form-data">
                        <label for="description">Description:</label>
                        <input type="text" id="description" name="description" minlength="5" required>

                        <label for="locaton">Location:</label>
                        <input type="text" id="locaton" name="locaton">

                        <div id="collaborators">
                            <label>Collaborator :
                                <input type="text" name="collaborators[]" required>
                            </label>
                        </div>
                        <button type="button" onclick="addCollaborator()">Add collaborator</button>
                        <br><br>

                        <button type="submit">Share</button>
                    </form>
                </div>
            </main>
        </div>

        <script src="${pageContext.request.contextPath}/scripts/displayImagePreview.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/addCollaborator.js"></script>

    </body>
</html>
