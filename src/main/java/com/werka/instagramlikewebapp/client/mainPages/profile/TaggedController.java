package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/profile/tagged")
public class TaggedController extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("file", "tagged");
        List<Post> posts = postService.getUserTaggedPosts();
        Collections.reverse(posts);
        req.setAttribute("taggedPosts", posts);
        req.setAttribute("taggedPostsQuantity", posts.size());
        req.getRequestDispatcher("/profile").forward(req, resp);
    }

}
