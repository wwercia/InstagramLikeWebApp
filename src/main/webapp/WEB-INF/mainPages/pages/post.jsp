<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 17.05.2025
  Time: 14:16
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

                <h1>Post!</h1>

                <c:forEach var="post" items="${requestScope.posts}">
                    <div class="post">
                        <img src="${pageContext.request.contextPath}/uploads/${post.imageName}${post.extension}" alt="Your post"/>
                        <p>${post.description}</p>
                        <c:if test="${post.location != null}">
                            <p>Location: ${post.location}</p>
                        </c:if>
                        <p>${post.likes} likes</p>
                    </div>
                </c:forEach>


            </main>
        </div>
    </body>
</html>
