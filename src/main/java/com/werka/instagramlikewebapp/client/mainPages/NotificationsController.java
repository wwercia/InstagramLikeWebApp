package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.domain.daos.posts.Comment;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.dto.CommentDto;
import com.werka.instagramlikewebapp.domain.dto.FollowDto;
import com.werka.instagramlikewebapp.domain.dto.LikeDto;
import com.werka.instagramlikewebapp.domain.services.PostService;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/notifications")
public class NotificationsController extends HttpServlet {

    private final PostService postService = new PostService();
    private final ProfileService profileService = new ProfileService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");

        List<CommentDto> comments = postService.getCommentsFromLastTwoMonths(currentUser.getId());
        req.setAttribute("comments", comments);

        List<LikeDto> likes = postService.getLikesFromLastTwoMonths(currentUser.getId());
        req.setAttribute("likes", likes);

        List<FollowDto> follows = profileService.getFollowsFromLastTwoMonths(currentUser.getId());
        for(FollowDto followDto : follows) {
            System.out.println(followDto);
        }
        req.setAttribute("follows", follows);


        req.getRequestDispatcher("/WEB-INF/mainPages/pages/notifications.jsp").forward(req, resp);
    }

}
