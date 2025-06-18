package com.werka.instagramlikewebapp.client.mainPages.posts;

import com.werka.instagramlikewebapp.domain.api.CommentDto;
import com.werka.instagramlikewebapp.domain.daos.posts.Comment;
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
import java.util.List;

@WebServlet("/post")
public class PostController extends HttpServlet {

    private final PostService postService = new PostService();
    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        String imageName = req.getParameter("imageName");

        int userId = postService.getPostByImageName(imageName).getUserId();
        UserProfile userProfile = profileService.getProfileInfo(userId);
        req.setAttribute("profileImageName", userProfile.getProfileImageName());
        req.setAttribute("username", userProfile.getUsername());

        Post post = postService.getPostByImageName(imageName);
        req.setAttribute("post", post);

        boolean isPostSavedInUserSavedPosts = postService.isPostSavedInUserSavedPosts(imageName, currentUser.getId());
        req.setAttribute("isPostSaved", isPostSavedInUserSavedPosts);

        boolean isPostLiked = postService.isPostLikedByUser(imageName, currentUser.getId());
        req.setAttribute("isPostLiked", isPostLiked);

        List<CommentDto> comments = postService.getComments(imageName, currentUser.getId());
        req.setAttribute("comments", comments);

        req.getRequestDispatcher("/WEB-INF/mainPages/pages/post.jsp").forward(req, resp);
    }
}
