package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.dto.CommentDto;
import com.werka.instagramlikewebapp.domain.dto.FollowDto;
import com.werka.instagramlikewebapp.domain.dto.LikeDto;
import com.werka.instagramlikewebapp.domain.dto.TagDto;
import com.werka.instagramlikewebapp.domain.services.PostService;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
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
        req.setAttribute("commentsQuantity", comments.size());

        List<LikeDto> likes = postService.getLikesFromLastTwoMonths(currentUser.getId());
        req.setAttribute("likes", likes);
        req.setAttribute("likesQuantity", likes.size());

        List<FollowDto> follows = profileService.getFollowsFromLastTwoMonths(currentUser.getId());
        req.setAttribute("follows", follows);
        req.setAttribute("followsQuantity", follows.size());

        List<TagDto> tags = postService.getTagsFromLastTwoMonths(currentUser.getId());
        req.setAttribute("tags", tags);
        req.setAttribute("tagsQuantity", tags.size());


        req.getRequestDispatcher("/WEB-INF/mainPages/pages/notifications.jsp").forward(req, resp);
    }

}
