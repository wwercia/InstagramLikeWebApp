<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 09.04.2025
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/registrationStyles.css">
    </head>
    <body>

        <div class="main-container">

            <h1>Sharrie</h1>

            <h2>Create an account</h2>
            <form action="registration" method="post">
                <label for="email">email</label>
                <input id="email" type="email" name="email">
                <p class="info">${requestScope.information}</p>

                <label for="password">password</label>
                <input id="password" type="password" name="password">

                <label for="username">username</label>
                <input id="username" type="text" name="username">

                <label for="firstName">First name</label>
                <input id="firstName" type="text" name="firstName">

                <label for="lastName">Last name</label>
                <input id="lastName" type="text" name="lastName">

                <label for="age">Age (over 13)</label>
                <input id="age" type="number" name="age" min="13">

                <button>Register</button>
            </form>
        </div>

    </body>
</html>
