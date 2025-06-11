package com.werka.instagramlikewebapp.client.mainPages.posts;

import com.werka.instagramlikewebapp.domain.daos.user.User;
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
        User currentUser = (User) req.getSession(false).getAttribute("user");

        System.out.println(currentUser.getUsername() + " " + currentUser.getId());

        String imageName = req.getParameter("imageName");
        if(!postService.isPostSavedInUserSavedPosts(imageName, currentUser.getId()))
            postService.savePostToUserSavedPosts(imageName, currentUser.getId());
        else
            postService.removePostFromUserSavedPosts(imageName);
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
