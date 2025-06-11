package com.werka.instagramlikewebapp.client.mainPages.posts;

import com.werka.instagramlikewebapp.domain.daos.posts.Comment;
import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/comment")
public class CommentController extends HttpServlet {

    private PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        String imageName = req.getParameter("imageName");
        String comment = req.getParameter("comment");
        postService.addComment(imageName, comment, currentUser.getUsername());
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
