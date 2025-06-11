package com.werka.instagramlikewebapp.client.mainPages.posts;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/like")
public class LikeController extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        String imageName = req.getParameter("imageName");
        boolean isPostLiked =  postService.isPostLikedByUser(imageName, currentUser.getId());
        if(!isPostLiked)
            postService.likePost(imageName, currentUser.getId());
        else
            postService.dislikePost(imageName);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
