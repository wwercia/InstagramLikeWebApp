package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/profile/follow")
public class FollowController extends HttpServlet {

    private ProfileService profileService = new ProfileService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        int followed_id = Integer.parseInt(req.getParameter("followed_id"));

        boolean isUserFollowed =  profileService.isUserFollowed(currentUser.getId(), followed_id);
        if(!isUserFollowed)
            profileService.follow(currentUser.getId(), followed_id);
        else
            profileService.unfollow(currentUser.getId(), followed_id);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
