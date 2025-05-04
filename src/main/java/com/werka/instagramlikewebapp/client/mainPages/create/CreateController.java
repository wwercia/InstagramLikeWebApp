package com.werka.instagramlikewebapp.client.mainPages.create;

import com.werka.instagramlikewebapp.domain.services.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/create")
public class CreateController extends HttpServlet  {

    private PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isImageAdded", false);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String newImageName = postService.savePost(1, "blank", "dobry dzień miałam haha", "Warszawa", 5);
        String originalFileName = filePart.getSubmittedFileName();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        filePart.write("C:\\SharrieUploads\\" + newImageName + extension);

        req.setAttribute("isImageAdded", true);
        req.setAttribute("imageName", newImageName + extension);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/create.jsp").forward(req, resp);
    }
}
