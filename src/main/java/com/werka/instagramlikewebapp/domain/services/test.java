package com.werka.instagramlikewebapp.domain.services;

import java.nio.file.Path;
import java.nio.file.Paths;

public class test {

    public static void main(String[] args) {
        Path path = Paths.get("C:\\SharrieUploads\\post.jpg");
        System.out.println(path.getFileName());
    }

}
