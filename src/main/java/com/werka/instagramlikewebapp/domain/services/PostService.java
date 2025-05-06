package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.posts.PostDao;

import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();

    public String savePostAndCollaborators(int userId, String imageName, String desciption, String location, int likes, List<String> collaborators) {
        int postId = postDao.savePost(userId, imageName, desciption, location, likes);
        postDao.saveCollaborators(postId, collaborators);
        String newImageName = "post" + postId;
        postDao.changeImageName(newImageName);
        return newImageName;
    }

    public void saveCollaborators(int postId, List<String> collaborators) {

    }

}
