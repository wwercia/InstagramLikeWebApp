package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.posts.PostDao;

public class PostService {

    private PostDao postDao = new PostDao();

    public String savePost(int userId, String imageName, String desciption, String location, int likes) {
        String newImageName = postDao.savePost(userId, imageName, desciption, location, likes);
        postDao.changeImageName(newImageName);
        return newImageName;
    }

}
