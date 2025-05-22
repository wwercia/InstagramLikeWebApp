package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile/saved")
public class SavedController extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("file", "saved");
        req.removeAttribute("posts");
        //List<Post> posts = postService.getUserSavedPosts();
        List<Post> posts = new ArrayList<>();
        //Collections.reverse(posts);
        req.setAttribute("savedPosts", posts);
        req.getRequestDispatcher("/profile").forward(req, resp);
    }

}
