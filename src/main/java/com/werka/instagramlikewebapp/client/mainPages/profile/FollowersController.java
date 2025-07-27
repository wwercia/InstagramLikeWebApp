package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.api.FollowerDto;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/profile/followers")
public class FollowersController extends HttpServlet {

    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userId"));

        UserProfile profileInfo;
        String username = req.getParameter("username");
        if(username == null) {
            profileInfo = profileService.getProfileInfo(userId);
        } else {
            profileInfo = profileService.getProfileInfoByUsername(username);
        }

        boolean isThisProfileMine = userId == profileInfo.getUserId();
        req.setAttribute("isThisProfileMine", isThisProfileMine);

        boolean isUserFollowed =  profileService.isUserFollowed(userId, profileInfo.getUserId());
        req.setAttribute("isUserFollowed", !isUserFollowed);

        req.setAttribute("userId", profileInfo.getUserId());
        req.setAttribute("username", profileInfo.getUsername());
        req.setAttribute("postsQuantity", profileInfo.getPostsQuantity());
        req.setAttribute("followersQuantity", profileInfo.getFollowers());
        req.setAttribute("followingQuantity", profileInfo.getFollowing());
        req.setAttribute("bio", profileInfo.getBio());
        req.setAttribute("profileImageName", profileInfo.getProfileImageName());

        List<FollowerDto> followers =  profileService.getFollowers(userId);
        req.setAttribute("followers", followers);

        req.getRequestDispatcher("/WEB-INF/mainPages/pages/followers.jsp").forward(req, resp);
    }
}
