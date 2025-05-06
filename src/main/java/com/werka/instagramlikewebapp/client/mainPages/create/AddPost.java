package com.werka.instagramlikewebapp.client.mainPages.create;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addPost")
public class AddPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("description");
        String location = req.getParameter("location");
        String collaborators = req.getParameter("collaborators");



        req.getRequestDispatcher("/WEB-INF/mainPages/pages/profile.jsp").forward(req, resp);
    }

}
