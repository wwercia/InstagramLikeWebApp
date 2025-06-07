package com.werka.instagramlikewebapp.client.mainPages.posts;

import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/post")
public class PostController extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("imageName");
        Post post = postService.getPostByImageName(imageName);
        req.setAttribute("post", post);
        boolean isPostSavedInUserSavedPosts = postService.isPostSavedInUserSavedPosts(imageName);
        req.setAttribute("isPostSaved", isPostSavedInUserSavedPosts);
        boolean isPostLiked = postService.isPostLikedByUser(imageName);
        req.setAttribute("isPostLiked", isPostLiked);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/post.jsp").forward(req, resp);
    }
}
