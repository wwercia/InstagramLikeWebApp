package com.werka.instagramlikewebapp.client.mainPages;

import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/create")
public class CreateController extends HttpServlet  {

    private PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("description");
        String location = req.getParameter("location");
        List<String> collab = new ArrayList<>();
        String[] collaborators = req.getParameterValues("collaborators");
        if (collaborators != null) {
            for (String collaborator : collaborators) {
                if (!collaborator.trim().isEmpty()) {
                    collab.add(collaborator);
                }
            }
        }

        Part filePart = req.getPart("file");
        String originalFileName = filePart.getSubmittedFileName();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newImageName = postService.savePostAndCollaborators(1, "blank", description, location, 0, collab, extension);
        filePart.write("C:\\SharrieUploads\\" + newImageName + extension);

        req.setAttribute("imageName", newImageName + extension);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/create.jsp").forward(req, resp);
    }
}
