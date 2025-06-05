package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.daos.posts.Post;
import com.werka.instagramlikewebapp.domain.daos.posts.PostDao;
import com.werka.instagramlikewebapp.domain.daos.posts.SavedPostDao;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();
    private SavedPostDao savedPostDao = new SavedPostDao();

    public String savePostAndCollaborators(int userId, String imageName, String description, String location, int likes, List<String> collaborators, String extension) {
        int postId = postDao.savePost(userId, imageName, description, location, likes, extension);
        String newImageName = "post" + postId;
        postDao.changeImageName(newImageName);
        postDao.changeImageExtension(extension, postId);
        postDao.saveCollaborators(postId, collaborators);
        postDao.increasePostsQuantity();
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
    public void savePostToUserSavedPosts(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        savedPostDao.savePostToUserSavedPosts(postID);
    }

    public List<Post> getUserSavedPosts() {
        List<Integer> savedPostIds = savedPostDao.getUserSavedPosts();
        if(savedPostIds.isEmpty()) {
            return new ArrayList<>();
        }
        return postDao.getUserSavedPostsByPostIds(savedPostIds);
    }

    public boolean isPostSavedInUserSavedPosts(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        return savedPostDao.isPostSavedInUserSavedPosts(postID);
    }

    public void removePostFromUserSavedPosts(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        savedPostDao.removePostFromUserSavedPosts(postID);
    }

}
