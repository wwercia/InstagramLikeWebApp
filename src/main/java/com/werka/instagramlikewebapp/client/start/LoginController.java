package com.werka.instagramlikewebapp.client.start;

import com.werka.instagramlikewebapp.domain.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController  extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/start/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(loginService.logIn(email, password)) {
            req.getRequestDispatcher("/WEB-INF/mainPages/pages/home.jsp").forward(req, resp);
        }else {
            req.setAttribute("information", "Incorrect data");
            req.getRequestDispatcher("/WEB-INF/start/index.jsp").forward(req, resp);
        }
    }
}
