package com.werka.instagramlikewebapp.client.mainPages.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/profile/posts")
public class PostsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("file", "posts");
        //resp.sendRedirect(req.getContextPath() + "/profile");
        req.getRequestDispatcher("/profile").forward(req, resp);
    }
}
