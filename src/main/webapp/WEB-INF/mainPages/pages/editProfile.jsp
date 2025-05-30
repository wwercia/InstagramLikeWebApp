<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 24.05.2025
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Sharrie</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/segments/sidebarStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/editProfileStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/basicStyles.css">
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf"%>
            <main>

                <div class="form-wrapper">
                    <form action="${pageContext.request.contextPath}/profile/editProfile" enctype="multipart/form-data" method="post" class="form1">
                        <label for="fileUpload">Add new profile image:</label>
                        <input type="file" id="fileUpload" name="file" accept="image/*" class="input" required>
                        <img id="preview" class="image-preview"/>
                        <button>Submit</button>
                    </form>

                    <form action="${pageContext.request.contextPath}/profile/editProfile" method="post" class="form2">
                        <label for="username">Username</label>
                        <input id="username" type="text" name="username" value="${requestScope.profile.username}" required>
                        <button>Submit</button>
                    </form>

                    <form action="${pageContext.request.contextPath}/profile/editProfile" method="post" class="form3">
                        <label for="bio">Bio</label>
                        <textarea id="bio" name="bio" rows="4" maxlength="200" required>${requestScope.profile.bio}</textarea>
                        <button>Submit</button>
                    </form>
                </div>

            </main>
        </div>

        <script>
            const fileInput = document.getElementById('fileUpload');
            const preview = document.getElementById('preview');

            fileInput.addEventListener('change', (e) => {
                const file = e.target.files[0];
                if (file) {
                    preview.src = URL.createObjectURL(file);
                }
            });
        </script>

    </body>
</html>
