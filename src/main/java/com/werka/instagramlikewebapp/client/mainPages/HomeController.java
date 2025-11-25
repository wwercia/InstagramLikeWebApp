package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.dto.FollowerDto;
import com.werka.instagramlikewebapp.domain.dto.HomePostDto;
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

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private final PostService postService = new PostService();
    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");

        if (currentUser != null) {
            String pagee = req.getParameter("page");
            int page = 0;

            if(pagee != null && !pagee.isEmpty()){
                page = Integer.parseInt(pagee);
            }
            int offset = page * 10;

            List<HomePostDto> posts = postService.getPostsForHome(currentUser.getId(), currentUser, offset);
            Collections.reverse(posts);
            req.setAttribute("posts", posts);
            page++;
            req.setAttribute("page", page);
            req.setAttribute("postsQuantity", posts.size());
            req.getRequestDispatcher("/WEB-INF/mainPages/pages/home.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/start/index.jsp").forward(req, resp);
        }
    }

}
