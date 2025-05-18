package com.werka.instagramlikewebapp.client.mainPages;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String imageName = req.getParameter("imageName");
        Post post = postService.getPostByImageName(imageName);
        System.out.println(post);
        req.setAttribute("post", post);

        req.getRequestDispatcher("/WEB-INF/mainPages/pages/post.jsp").forward(req, resp);
    }
}
