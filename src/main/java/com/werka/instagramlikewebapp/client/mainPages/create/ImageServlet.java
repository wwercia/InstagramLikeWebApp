package com.werka.instagramlikewebapp.client.mainPages.create;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private static final String IMAGE_DIR = "C://SharrieUploads/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String imageName = req.getPathInfo();
        if (imageName == null || imageName.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brak nazwy pliku.");
            return;
        }

        // Usuwamy "/ na początku, bo req.getPathInfo() zawiera pełną ścieżkę
        imageName = imageName.substring(1);

        File imageFile = new File(IMAGE_DIR, imageName);
        if (!imageFile.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Plik nie istnieje: " + imageFile.getAbsolutePath());
            return;
        }

        String mimeType = getServletContext().getMimeType(imageFile.getName());
        if (mimeType == null) mimeType = "application/octet-stream"; // domyślny typ

        resp.setContentType(mimeType);

        // Wysyłanie obrazu do przeglądarki
        try (ServletOutputStream out = resp.getOutputStream()) {
            Files.copy(imageFile.toPath(), out);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Błąd serwera: " + e.getMessage());
        }
    }

}
