package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.services.PostService;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private ProfileService profileService = new ProfileService();
    private PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile profileInfo = profileService.getProfileInfo(DataHelper.getUser().getId());
        req.setAttribute("username", profileInfo.getUsername());
        req.setAttribute("postsQuantity", profileInfo.getPostsQuantity());
        req.setAttribute("followers", profileInfo.getFollowers());
        req.setAttribute("following", profileInfo.getFollowing());
        req.setAttribute("bio", profileInfo.getBio());
        List<Post> posts = postService.getUserPosts();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
