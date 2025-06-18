<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sharrie</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/start/indexStyles.css">
    </head>
    <body>

        <div class="main-container">

            <img src="${pageContext.request.contextPath}/images/sharrie%20main%20image.png" alt="sharrie image">

            <div class="info-container">
                <h1>Sharrie</h1>

                <form action="login" method="post">
                    <label for="email">email</label>
                    <input id="email" type="email" name="email">

                    <label for="password">password</label>
                    <input id="password" type="password" name="password">

                    <button>Log in</button>
                    <p class="info">${requestScope.information}</p>
                </form>

                <h3>You don't have an account?</h3>
                <a href="<%=request.getContextPath() + "/registration"%>">register</a>

            </div>

        </div>


    </body>
</html>