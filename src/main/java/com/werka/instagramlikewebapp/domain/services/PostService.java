package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.daos.posts.PostDao;

import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();

    public String savePostAndCollaborators(int userId, String imageName, String desciption, String location, int likes, List<String> collaborators, String extension) {
        int postId = postDao.savePost(userId, imageName, desciption, location, likes, extension);
        String newImageName = "post" + postId;
        postDao.changeImageName(newImageName);
        postDao.changeImageExtension(extension, postId);
        postDao.saveCollaborators(postId, collaborators);
        return newImageName;
    }


    public List<Post> getUserPosts() {
        return postDao.getPostsByUserId(DataHelper.getUser().getId());
    }

    public Post getPostByImageName(String imageName) {
        return postDao.getPostByImageName(imageName);
    }

    public List<Post> getUserTaggedPosts() {
        return postDao.getUserTaggedPostsByUserId(DataHelper.getUser().getId());
    }

}
