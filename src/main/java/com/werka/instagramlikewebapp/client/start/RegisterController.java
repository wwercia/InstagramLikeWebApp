package com.werka.instagramlikewebapp.client.start;

import com.werka.instagramlikewebapp.domain.RegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegisterController extends HttpServlet {

    private RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("information", "");
        req.getRequestDispatcher("/WEB-INF/start/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Integer age = Integer.parseInt(req.getParameter("age"));

        if(registerService.register(email, password, username, firstName, lastName, age)) {
            req.getRequestDispatcher("/WEB-INF/mainPages/pages/home.jsp").forward(req, resp);
        } else {
            req.setAttribute("information", "It looks like you already have an account with us, please log in.");
            req.getRequestDispatcher("/WEB-INF/start/registration.jsp").forward(req, resp);
        }

    }
}
