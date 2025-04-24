<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 17.04.2025
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Sharrie</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/segments/sidebarStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/basicStyles.css">
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf"%>
            <main>

                <div class="profile-information">
                    <img src="${pageContext.request.contextPath}/images/icons/create%20icon.png" alt="profile icon">
                    <p class="username">${requestScope.username}</p>

                    <form action="profile" method="post">
                        <button type="submit">Edit profile</button>
                    </form>

                    <p>${requestScope.postsQuantity} post</p>
                    <p>${requestScope.followers} followers</p>
                    <p>${requestScope.following} following</p>
                    <p>${requestScope.bio}</p>
                </div>

                <div class="bottom-box">

                    <div class="photos-options">
                        <form action="posts" method="get">
                            <button type="submit">Posts</button>
                        </form>
                        <form action="profile" method="get">
                            <button type="submit">Saved</button>
                        </form>
                        <form action="profile" method="get">
                            <button type="submit">Tagged</button>
                        </form>
                    </div>

                    <div class="photos">

                    </div>

                </div>

            </main>
        </div>
    </body>
</html>
