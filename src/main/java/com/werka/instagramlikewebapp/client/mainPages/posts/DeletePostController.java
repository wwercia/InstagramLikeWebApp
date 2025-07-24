package com.werka.instagramlikewebapp.client.mainPages.posts;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletePost")
public class DeletePostController extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("postId"));
        postService.deletePost(postId);

        StringBuilder builder = new StringBuilder(req.getHeader("Referer"));
        int index = builder.indexOf("post");
        builder.setLength(index);
        builder.append("profile");

        resp.sendRedirect(builder.toString());
    }
}
