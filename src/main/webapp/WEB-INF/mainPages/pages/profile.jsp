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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/profileStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/segments/postsStyles.css">
    </head>
        <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf" %>
            <main>

                <div class="profile-information">
                    <div class="top-profile-info">

                        <c:if test="${empty requestScope.profileImageName}">
                            <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/create%20icon.png" alt="profile icon">
                        </c:if>

                        <c:if test="${not empty requestScope.profileImageName}">
                            <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${requestScope.profileImageName}" alt="profile icon">
                        </c:if>

                        <p class="username">${requestScope.username}</p>

                        <form action="${pageContext.request.contextPath}/profile/editProfile" method="get">
                            <button type="submit">Edit profile</button>
                        </form>
                    </div>
                    <div class="bottom-profile-info">
                        <p>${requestScope.postsQuantity} posts</p>
                        <p>${requestScope.followers} followers</p>
                        <p>${requestScope.following} following</p>
                        <p style="white-space: pre-line;">${requestScope.bio}</p>
                    </div>
                </div>

                <div class="bottom-box">

                    <div class="photos-options">
                        <form action="${pageContext.request.contextPath}/profile/posts" method="get">
                            <button class="option-button" type="submit">Posts</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/profile/saved" method="get">
                            <button class="option-button" type="submit">Saved</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/profile/tagged" method="get">
                            <button class="option-button" type="submit">Tagged</button>
                        </form>
                    </div>

                    <div class="photos">
                        <c:if test="${requestScope.file == null}">
                            <jsp:include page="../segments/profile/posts.jspf"/>
                        </c:if>
                        <c:if test="${requestScope.file != null}">
                            <jsp:include page="../segments/profile/${requestScope.file}.jspf"/>
                        </c:if>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
