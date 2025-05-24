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
    </head>
    <body>
        <div class="main-container">
            <%@include file="../segments/sidebar.jspf" %>
            <main>
                <h1>Post!</h1>

                <div class="post">
                    <img src="${pageContext.request.contextPath}/uploads/${requestScope.post.imageName}${requestScope.post.extension}" alt="Your post"/>
                    <p>${requestScope.post.description}</p>
                    <c:if test="${not empty requestScope.post.location and requestScope.post.location != 'null'}">
                        <p>Location: ${requestScope.post.location}</p>
                    </c:if>
                    <p>${requestScope.post.likes} likes</p>
                </div>

            </main>
        </div>
    </body>
</html>
