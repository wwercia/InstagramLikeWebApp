<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 27.07.2025
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head>
        <title>Sharrie</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/segments/sidebarStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/basicStyles.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/followersStyles.css">
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf"%>
            <main>

                <div class="profile-information">
                    <div class="top-profile-info">

                        <c:if test="${empty requestScope.profileImageName}">
                            <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                        </c:if>

                        <c:if test="${not empty requestScope.profileImageName}">
                            <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${requestScope.profileImageName}" alt="profile icon">
                        </c:if>
                        <p class="username">${requestScope.username}</p>

                        <c:if test="${requestScope.isThisProfileMine}">
                            <form action="${pageContext.request.contextPath}/profile/editProfile" method="get">
                                <button type="submit">Edit profile</button>
                            </form>
                        </c:if>

                        <c:if test="${not requestScope.isThisProfileMine}">
                            <c:if test="${not requestScope.isUserFollowed}">
                                <form action="${pageContext.request.contextPath}/profile/follow" method="post">
                                    <input type="hidden" name="followed_id" value="${requestScope.userId}">
                                    <button id="unfollowButton" type="submit">Unfollow</button>
                                </form>
                            </c:if>

                            <c:if test="${requestScope.isUserFollowed}">
                                <form action="${pageContext.request.contextPath}/profile/follow" method="post">
                                    <input type="hidden" name="followed_id" value="${requestScope.userId}">
                                    <button type="submit">Follow</button>
                                </form>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="bottom-profile-info">
                        <p>${requestScope.postsQuantity} posts</p>
                        <form action="${pageContext.request.contextPath}/profile/followers" method="get" class="followers">
                            <input type="hidden" name="username" value="${requestScope.username}">
                            <button type="submit" class="followers-button">${requestScope.followersQuantity} followers</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/profile/following" method="get" class="followers">
                            <input type="hidden" name="username" value="${requestScope.username}">
                            <button type="submit" class="followers-button">${requestScope.followingQuantity} following</button>
                        </form>
                        <p style="white-space: pre-line;">${requestScope.bio}</p>
                    </div>
                </div>

                <div class="followers-section">
                    <h2 class="section-title">Followers</h2>

                    <c:if test="${empty requestScope.followers}">
                        <p>This user has no followers</p>
                    </c:if>

                    <div class="followers-list">
                        <c:forEach var="follower" items="${requestScope.followers}">
                            <div class="follower-row">
                                <c:if test="${empty follower.profileImageName}">
                                    <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                                </c:if>

                                <c:if test="${not empty follower.profileImageName}">
                                    <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${follower.profileImageName}" alt="profile icon">
                                </c:if>
                                <a href="${pageContext.request.contextPath}/profile?username=${follower.username}" class="follower-name">
                                        ${follower.username}
                                </a>
                            </div>
                        </c:forEach>
                    </div>

                </div>



            </main>
        </div>
    </body>
</html>
