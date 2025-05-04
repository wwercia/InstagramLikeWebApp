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
            <%@include file="../segments/sidebar.jspf"%>
            <main>

                <c:if test="${!requestScope.isImageAdded}">
                    <form action="${pageContext.request.contextPath}/create" method="POST" enctype="multipart/form-data">
                        <label for="fileUpload">Wybierz zdjęcie:</label>
                        <input type="file" id="fileUpload" name="file" accept="image/*">
                        <button type="submit">Prześlij</button>
                    </form>
                </c:if>

                <c:if test="${requestScope.isImageAdded}">

                    <img src="${pageContext.request.contextPath}/images/${requestScope.imageName}" alt="Your photo"/>

                </c:if>

            </main>
        </div>
    </body>
</html>
