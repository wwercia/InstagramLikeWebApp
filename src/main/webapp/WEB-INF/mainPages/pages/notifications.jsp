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
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf" %>
            <main>

                <p>Comments</p>
                <c:forEach var="comment" items="${requestScope.comments}">
                    <p>${comment.username} commented your post: "${comment.comment}"! on ${comment.date}</p>
                </c:forEach>

                <p>Likes</p>
                <c:forEach var="like" items="${requestScope.likes}">
                    <p>${like.followerUsername} liked your post! on ${like.date}</p>
                </c:forEach>

                <p>Follows</p>
                <c:forEach var="follow" items="${requestScope.follows}">
                    <p>${follow.followerUsername} followed you! on ${follow.date}</p>
                </c:forEach>

                <p>Tags</p>
                <c:forEach var="tag" items="${requestScope.tags}">
                    <p>${tag.username} tagged you on his/her post (postId = ${tag.postId})! on ${tag.date}</p>
                </c:forEach>

            </main>
        </div>
    </body>
</html>
