package com.werka.instagramlikewebapp.client.mainPages.profile;

import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.services.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet("/profile/editProfile")
public class EditProfileController extends HttpServlet {

    private ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        UserProfile profileInfo = profileService.getProfileInfo(currentUser.getId());
        req.setAttribute("profile", profileInfo);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        String username = req.getParameter("username");
        String bio = req.getParameter("bio");
        if(username != null) {
            profileService.updateUsername(username, currentUser.getId());

            currentUser.setUsername(username);
            session.setAttribute("user", currentUser);


        } else if(bio != null) {
            profileService.updateBio(bio, currentUser.getId());
        } else {
            deletePreviousProfileFiles(currentUser.getId());
            Part filePart = req.getPart("file");
            String originalFileName = filePart.getSubmittedFileName();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newImageName = "profile" + currentUser.getId() + extension;
            profileService.saveNewProfileImage(newImageName, currentUser.getId());
            filePart.write("C:\\SharrieUploads\\" + newImageName);
        }

        UserProfile profileInfo = profileService.getProfileInfo(currentUser.getId());
        req.setAttribute("profile", profileInfo);
        req.getRequestDispatcher("/WEB-INF/mainPages/pages/editProfile.jsp").forward(req, resp);
    }

    private void deletePreviousProfileFiles(int userId) {
        String uploadDir = "C:\\SharrieUploads\\";
        String baseName = "profile" + userId;

        File dir = new File(uploadDir);
        File[] matchingFiles = dir.listFiles((d, name) -> name.startsWith(baseName + "."));

        if (matchingFiles != null) {
            for (File file : matchingFiles) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Usunięto stary plik: " + file.getName());
                } else {
                    System.out.println("Nie udało się usunąć: " + file.getName());
                }
            }
        }
    }
}
