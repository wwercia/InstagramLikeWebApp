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

                <div class="forms-container">

                    <form action="${pageContext.request.contextPath}/create" method="POST" enctype="multipart/form-data" class="post-info-form">

                        <div class="image-container">
                            <label for="fileUpload">Choose an image:</label>
                            <input type="file" id="fileUpload" name="file" accept="image/*" class="input">
                            <img id="preview" class="image-preview"/>
                        </div>

                        <div class="info-container">
                            <label for="description">Description:</label>
                            <textarea id="description" name="description" class="description" rows="6" maxlength="200"></textarea>

                            <label for="location">Location:</label>
                            <input type="text" id="location" name="location" class="input" maxlength="55">

                            <div id="collaborators" class="collaborators">
                                <label class="collaborator-label">Collaborator:
                                    <input type="text" name="collaborators" class="collaborator-input">
                                </label>
                            </div>
                            <button id="addCollaboratorButton" type="button" onclick="addCollaborator()">Add collaborator</button>
                            <p id="collaboratorLimitMessage" style="color: red; display: none;">
                                The maximum number of collaborators is 10!
                            </p>
                            <button class="submit-button" type="submit">Share</button>
                        </div>

                    </form>
                </div>

            </main>

        </div>

        <script src="${pageContext.request.contextPath}/scripts/displayImagePreview.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/addCollaborator.js"></script>

    </body>
</html>
