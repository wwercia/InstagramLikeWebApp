package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.posts.*;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();
    private SavedPostDao savedPostDao = new SavedPostDao();
    private PostLikeDao postLikeDao = new PostLikeDao();
    private CommentDao commentDao = new CommentDao();

    public String savePostAndCollaborators(int userId, String imageName, String description, String location, int likes, List<String> collaborators, String extension) {
        int postId = postDao.savePost(userId, imageName, description, location, likes, extension);
        String newImageName = "post" + postId;
        postDao.changeImageName(newImageName);
        postDao.changeImageExtension(extension, postId);
        postDao.saveCollaborators(postId, collaborators);
        postDao.increasePostsQuantity(userId);
        return newImageName;
    }

    public List<Post> getUserPosts(int userID) {
        return postDao.getPostsByUserId(userID);
    }

    public Post getPostByImageName(String imageName) {
        return postDao.getPostByImageName(imageName);
    }

    public List<Post> getUserTaggedPosts(int userId) {
        return postDao.getUserTaggedPostsByUserId(userId);
    }
    public void savePostToUserSavedPosts(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        savedPostDao.savePostToUserSavedPosts(postID, userId);
    }

    public List<Post> getUserSavedPosts(int userId) {
        List<Integer> savedPostIds = savedPostDao.getUserSavedPosts(userId);
        if(savedPostIds.isEmpty()) {
            return new ArrayList<>();
        }
        return postDao.getUserSavedPostsByPostIds(savedPostIds);
    }

    public boolean isPostSavedInUserSavedPosts(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        return savedPostDao.isPostSavedInUserSavedPosts(postID, userId);
    }

    public void removePostFromUserSavedPosts(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        savedPostDao.removePostFromUserSavedPosts(postID);
    }

    public boolean isPostLikedByUser(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        return postLikeDao.isPostLikedByUser(postID, userId);
    }

    public void likePost(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        postLikeDao.saveLikeToPostLikes(postID, userId);
        postDao.increasePostLikes(postID);
    }

    public void dislikePost(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        postLikeDao.removeLikeFromPostLikes(postID);
        postDao.decreasePostLikes(postID);
    }

    public void addComment(String imageName, String comment, String username) {
        int postId = postDao.getPostIdByImageName(imageName);
        commentDao.saveComment(postId, comment, username);
    }

    public List<Comment> getComments(String imageName) {
        int postId = postDao.getPostIdByImageName(imageName);
        return commentDao.getCommentsByPostId(postId);
    }

}
