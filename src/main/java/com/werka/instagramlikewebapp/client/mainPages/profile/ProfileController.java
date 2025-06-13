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

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private ProfileService profileService = new ProfileService();
    private PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");

        UserProfile profileInfo;
        String username = req.getParameter("username");
        if(username == null) {
            profileInfo = profileService.getProfileInfo(currentUser.getId());
        } else {
            profileInfo = profileService.getProfileInfoByUsername(username);
        }

        boolean isThisProfileMine = currentUser.getId() == profileInfo.getUserId();
        req.setAttribute("isThisProfileMine", isThisProfileMine);

        boolean isUserFollowed =  profileService.isUserFollowed(currentUser.getId(), profileInfo.getUserId());
        req.setAttribute("isUserFollowed", !isUserFollowed);

        req.setAttribute("userId", profileInfo.getUserId());
        req.setAttribute("username", profileInfo.getUsername());
        req.setAttribute("postsQuantity", profileInfo.getPostsQuantity());
        req.setAttribute("followers", profileInfo.getFollowers());
        req.setAttribute("following", profileInfo.getFollowing());
        req.setAttribute("bio", profileInfo.getBio());
        req.setAttribute("profileImageName", profileInfo.getProfileImageName());

        List<Post> posts = postService.getUserPosts(profileInfo.getUserId());
        Collections.reverse(posts);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
