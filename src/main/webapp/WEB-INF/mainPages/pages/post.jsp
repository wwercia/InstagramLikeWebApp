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
                    <img src="${pageContext.request.contextPath}/uploads/${requestScope.post.imageName}${requestScope.post.extension}" alt="Your post"/>

                    <form id="form" action="${pageContext.request.contextPath}/save" method="get">
                        <c:if test="${not requestScope.isPostSaved}">
                            <img src="${pageContext.request.contextPath}/images/icons/post%20not%20saved.png" alt="post not saved icon"
                                 style="cursor:pointer"
                                 onclick="document.getElementById('form').submit();">
                        </c:if>
                        <c:if test="${requestScope.isPostSaved}">
                            <img src="${pageContext.request.contextPath}/images/icons/post%20saved.png" alt="post saved icon"
                                 style="cursor:pointer"
                                 onclick="document.getElementById('form').submit();">
                        </c:if>
                        <input type="hidden" name="imageName" value="${requestScope.post.imageName}" />
                    </form>

                    <p>${requestScope.post.description}</p>
                    <c:if test="${not empty requestScope.post.location and requestScope.post.location != 'null'}">
                        <p class="location">Location: ${requestScope.post.location}</p>
                    </c:if>

                    <form id="likesForm" action="${pageContext.request.contextPath}/like" method="post">
                        <c:if test="${not requestScope.isPostLiked}">
                            <img src="${pageContext.request.contextPath}/images/icons/heart%20icon.png" alt="not liked post heart icon"
                                 style="cursor:pointer"
                                 onclick="document.getElementById('likesForm').submit();">
                        </c:if>
                        <c:if test="${requestScope.isPostLiked}">
                            <img src="${pageContext.request.contextPath}/images/icons/liked%20heart%20icon.png" alt="liked post heart icon
                                 style="cursor:pointer"
                                 onclick="document.getElementById('likesForm').submit();">
                        </c:if>
                        <input type="hidden" name="imageName" value="${requestScope.post.imageName}" />
                    </form>

                    <p>${requestScope.post.likes} likes</p>
                </div>

            </main>

        </div>
    </body>
</html>
