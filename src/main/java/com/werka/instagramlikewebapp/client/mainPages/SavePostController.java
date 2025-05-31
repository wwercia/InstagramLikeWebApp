package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save")
public class SavePostController extends HttpServlet {

    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String imageName = req.getParameter("imageName");
        if(!postService.isPostSavedInUserSavedPosts(imageName))
            postService.savePostToUserSavedPosts(imageName);
        else
            postService.removePostFromUserSavedPosts(imageName);
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
