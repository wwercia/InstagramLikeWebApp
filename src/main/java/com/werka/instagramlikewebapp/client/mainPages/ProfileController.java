package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.api.ProfileInfo;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile profileInfo = profileService.getProfileInfo(DataHelper.getUser().getId());
        req.setAttribute("username", profileInfo.getUsername());
        req.setAttribute("postsQuantity", profileInfo.getPostsQuantity());
        req.setAttribute("followers", profileInfo.getFollowers());
        req.setAttribute("following", profileInfo.getFollowing());
        req.setAttribute("bio", profileInfo.getBio());
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
