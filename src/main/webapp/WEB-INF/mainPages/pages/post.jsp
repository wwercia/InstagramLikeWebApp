<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 17.05.2025
  Time: 14:16
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/pages/postStyles.css">
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf" %>

            <main>

                <div class="post">
                    <div class="post-image">
                        <img id="postImage" src="${pageContext.request.contextPath}/uploads/${requestScope.post.imageName}${requestScope.post.extension}" alt="Your post"/>
                    </div>

                    <div class="post-content">

                        <div class="user-data-container">
                            <c:if test="${empty requestScope.profileImageName}">
                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/images/icons/user%20icon.png" alt="profile icon">
                            </c:if>
                            <c:if test="${not empty requestScope.profileImageName}">
                                <img class="user-profile-photo" src="${pageContext.request.contextPath}/uploads/${requestScope.profileImageName}" alt="profile icon">
                            </c:if>
                            <p class="username">${requestScope.username}</p>
                        </div>

                        <div class="description-bar">
                            <p class="description-text">${requestScope.post.description}</p>
                            <form id="saveForm" action="${pageContext.request.contextPath}/save" method="get" class="icon-form">
                                <button type="submit" class="icon-button">
                                    <c:choose>
                                        <c:when test="${requestScope.isPostSaved}">
                                            <img src="${pageContext.request.contextPath}/images/icons/post%20saved.png" alt="post saved icon" />
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/images/icons/post%20not%20saved.png" alt="post not saved icon" />
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                                <input type="hidden" name="imageName" value="${requestScope.post.imageName}" />
                            </form>
                        </div>


                        <!-- Location -->
                        <c:if test="${not empty requestScope.post.location and requestScope.post.location != 'null'}">
                            <p class="location">Location: ${requestScope.post.location}</p>
                        </c:if>

                        <form id="likesForm" action="${pageContext.request.contextPath}/like" method="post" class="likes-form">
                            <button type="submit" class="like-button">
                                <c:choose>
                                    <c:when test="${requestScope.isPostLiked}">
                                        <img src="${pageContext.request.contextPath}/images/icons/liked%20heart%20icon.png" alt="liked post heart icon" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/images/icons/heart%20icon.png" alt="not liked post heart icon" />
                                    </c:otherwise>
                                </c:choose>
                            </button>
                            <span class="likes-count">${requestScope.post.likes} likes</span>
                            <input type="hidden" name="imageName" value="${requestScope.post.imageName}" />
                        </form>


                        <!-- Comment form -->
                        <form action="${pageContext.request.contextPath}/comment" method="post" class="comment-form">
                            <label for="comment"></label>
                            <textarea id="comment" name="comment" rows="3" maxlength="50" required placeholder="Your comment..."></textarea>
                            <input type="hidden" name="imageName" value="${requestScope.post.imageName}" />
                            <button>Add comment</button>
                        </form>

                        <!-- Scrollable comments -->
                        <div class="comments-scroll">
                            <c:forEach var="comment" items="${requestScope.comments}">
                                <div class="comment">
                                    <p>${comment.username}</p>
                                    <p>${comment.comment}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>



            </main>

        </div>
    </body>
</html>
