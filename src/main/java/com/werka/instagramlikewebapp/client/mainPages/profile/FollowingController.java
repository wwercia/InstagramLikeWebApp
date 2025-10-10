package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.dto.FollowerDto;
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

@WebServlet("/profile/following")
public class FollowingController extends HttpServlet {

    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");

        String username = req.getParameter("username");
        UserProfile profileInfo = profileService.getProfileInfoByUsername(username);

        boolean isThisProfileMine = currentUser.getId() == profileInfo.getUserId();
        req.setAttribute("isThisProfileMine", isThisProfileMine);

        boolean isUserFollowed =  profileService.isUserFollowed(currentUser.getId(), profileInfo.getUserId());
        req.setAttribute("isUserFollowed", !isUserFollowed);

        req.setAttribute("userId", profileInfo.getUserId());
        req.setAttribute("username", profileInfo.getUsername());
        req.setAttribute("postsQuantity", profileInfo.getPostsQuantity());
        req.setAttribute("followersQuantity", profileInfo.getFollowers());
        req.setAttribute("followingQuantity", profileInfo.getFollowing());
        req.setAttribute("bio", profileInfo.getBio());
        req.setAttribute("profileImageName", profileInfo.getProfileImageName());

        List<FollowerDto> following =  profileService.getFollowing(profileInfo.getUserId());
        req.setAttribute("following", following);

        req.getRequestDispatcher("/WEB-INF/mainPages/pages/following.jsp").forward(req, resp);
    }

}
