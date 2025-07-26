package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser != null) {
            req.getRequestDispatcher("/WEB-INF/mainPages/pages/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/start/index.jsp").forward(req, resp);
        }
    }

}
