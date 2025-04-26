package com.werka.instagramlikewebapp.client.mainPages.profile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/profile/tagged")
public class TaggedController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("file", "tagged");
        //resp.sendRedirect(req.getContextPath() + "/profile");
        req.getRequestDispatcher("/profile").forward(req, resp);
    }

}
