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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/notificationsStyles.css">
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf" %>


            <main class="notifications-container">

                <h2 class="section-title">Notifications</h2>

                <div class="notifications-grid">

                    <!-- Left column -->
                    <div class="notifications-column">

                        <div class="notification-section">
                            <h3>Comments</h3>
                            <div class="notification-list">
                                <c:forEach var="comment" items="${requestScope.comments}">
                                    <div class="notification-item">
                                        <c:choose>
                                            <c:when test="${empty comment.profileImageName}">
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${comment.profileImageName}" alt="profile icon">
                                            </c:otherwise>
                                        </c:choose>
                                        <p>
                                            <span class="username"><a href="${pageContext.request.contextPath}/profile?username=${comment.username}" class="usernameComment">
                                                    ${comment.username}
                                            </a></span> commented your
                                            <span class="post"><a href="${pageContext.request.contextPath}/post?imageName=post${comment.postId}" class="postt">
                                                    post
                                            </a></span>:
                                            <span class="message">"${comment.comment}"</span>
                                            <span class="date">(${comment.date})</span>
                                        </p>
                                    </div>
                                </c:forEach>
                                <c:if test="${requestScope.commentsQuantity == 0}">
                                    <div class="containerForNoNotificationsText">
                                        <p  class="noNotificationsText">No one commented on your posts in the last two months!</p>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="notification-section">
                            <h3>Likes</h3>
                            <div class="notification-list">
                                <c:forEach var="like" items="${requestScope.likes}">
                                    <div class="notification-item">
                                        <c:choose>
                                            <c:when test="${empty like.profileImageName}">
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${like.profileImageName}" alt="profile icon">
                                            </c:otherwise>
                                        </c:choose>
                                        <p>
                                            <span class="username"><a href="${pageContext.request.contextPath}/profile?username=${like.username}" class="usernameComment">
                                                    ${like.username}
                                            </a></span>liked your
                                            <span class="post"><a href="${pageContext.request.contextPath}/post?imageName=post${like.postId}" class="postt">
                                                    post
                                            </a></span>
                                            <span class="date">(${like.date})</span>
                                        </p>
                                    </div>
                                </c:forEach>
                                <c:if test="${requestScope.likesQuantity == 0}">
                                    <div class="containerForNoNotificationsText">
                                        <p  class="noNotificationsText">No one liked your posts in the last two months!</p>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <!-- Right column -->
                    <div class="notifications-column">

                        <div class="notification-section">
                            <h3>Follows</h3>
                            <div class="notification-list">
                                <c:forEach var="follow" items="${requestScope.follows}">
                                    <div class="notification-item">
                                        <c:choose>
                                            <c:when test="${empty follow.profileImageName}">
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${follow.profileImageName}" alt="profile icon">
                                            </c:otherwise>
                                        </c:choose>
                                        <p>
                                            <span class="username"><a href="${pageContext.request.contextPath}/profile?username=${follow.followerUsername}" class="usernameComment">
                                                    ${follow.followerUsername}
                                            </a></span> followed you
                                            <span class="date">(${follow.date})</span>
                                        </p>
                                    </div>
                                </c:forEach>
                                <c:if test="${requestScope.followsQuantity == 0}">
                                    <div class="containerForNoNotificationsText">
                                        <p  class="noNotificationsText">No one followed you in the last two months!</p>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="notification-section">
                            <h3>Tags</h3>
                            <div class="notification-list">
                                <c:forEach var="tag" items="${requestScope.tags}">
                                    <div class="notification-item">
                                        <c:choose>
                                            <c:when test="${empty tag.profileImageName}">
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${tag.profileImageName}" alt="profile icon">
                                            </c:otherwise>
                                        </c:choose>
                                        <p>
                                            <span class="username"><a href="${pageContext.request.contextPath}/profile?username=${tag.username}" class="usernameComment">
                                                    ${tag.username}
                                            </a></span> tagged you in a
                                            <span class="post"><a href="${pageContext.request.contextPath}/post?imageName=post${tag.postId}" class="postt">
                                                    post
                                            </a></span>
                                            <span class="date">(${tag.date})</span>
                                        </p>
                                    </div>
                                </c:forEach>
                                <c:if test="${requestScope.tagsQuantity == 0}">
                                    <div class="containerForNoNotificationsText">
                                        <p  class="noNotificationsText">No one tagged your posts in the last two months!</p>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                    </div>

                </div>

            </main>


        </div>
    </body>
</html>
