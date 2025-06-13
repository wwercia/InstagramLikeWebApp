package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.PostService;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/profile/saved")
public class SavedController extends HttpServlet {

    private final PostService postService = new PostService();
    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserProfile profileInfo;
        String username = req.getParameter("username");
        if(username == null) {
            User currentUser = (User) req.getSession().getAttribute("user");
            profileInfo = profileService.getProfileInfo(currentUser.getId());
        } else {
            profileInfo = profileService.getProfileInfoByUsername(username);
        }

        req.setAttribute("file", "saved");
        req.removeAttribute("posts");
        List<Post> posts = postService.getUserSavedPosts(profileInfo.getUserId());
        Collections.reverse(posts);
        req.setAttribute("savedPosts", posts);
        req.setAttribute("savedPostsQuantity", posts.size());
        req.getRequestDispatcher("/profile").forward(req, resp);
    }

}
