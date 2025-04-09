<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sharrie</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/indexStyles.css">
    </head>
    <body>


        <div class="main-container">

            <img src="${pageContext.request.contextPath}/images/sharrie main image.png" alt="sharrie image">

            <div class="info-container">
                <h1>Sharrie</h1>

                <form>
                    <label for="email">email</label>
                    <input id="email" type="email">

                    <label for="password">password</label>
                    <input id="password" type="password">

                    <button>Log in</button>
                </form>

                <h3>You don't have an account?</h3>
                <a href="<%=request.getContextPath() + "/registration"%>">register</a>

            </div>

        </div>


    </body>
</html>