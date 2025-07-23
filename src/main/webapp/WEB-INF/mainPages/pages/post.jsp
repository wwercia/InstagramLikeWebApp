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
                            <a href="${pageContext.request.contextPath}/profile?username=${requestScope.username}" class="username">
                                ${requestScope.username}
                            </a>
                            <p class="addedAt">${requestScope.addedAt}</p>
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
                                    <div class="comment-body">
                                        <a href="${pageContext.request.contextPath}/profile?username=${comment.username}" class="usernameComment">
                                                ${comment.username}
                                        </a>
                                        <div class="comment-text">
                                                ${comment.comment}
                                        </div>
                                    </div>

                                    <c:if test="${comment.commentMine}">
                                        <div class="comment-actions">
                                            <form action="${pageContext.request.contextPath}/deleteComment" method="post" class="delete-comment-form">
                                                <input type="hidden" name="commentId" value="${comment.id}"/>
                                                <button class="icon-btn" title="Delete">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                                         stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-trash2-icon lucide-trash-2">
                                                        <path d="M10 11v6"/>
                                                        <path d="M14 11v6"/>
                                                        <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6"/>
                                                        <path d="M3 6h18"/>
                                                        <path d="M8 6V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/>
                                                    </svg>
                                                </button>
                                            </form>
                                        </div>
                                    </c:if>
                                </div>


                            </c:forEach>
                        </div>
                    </div>
                </div>



            </main>

        </div>
    </body>
</html>
