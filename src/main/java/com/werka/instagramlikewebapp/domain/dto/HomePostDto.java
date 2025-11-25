package com.werka.instagramlikewebapp.domain.dto;

import com.werka.instagramlikewebapp.domain.daos.posts.Comment;
import com.werka.instagramlikewebapp.domain.daos.posts.Post;

import java.util.List;

public class HomePostDto {

    private String profileImageName;
    private String username;
    private Post post;
    private boolean isPostSaved;
    private boolean isPostLiked;
    private List<CommentDto> comments;
    private String addedAt;
    private boolean isPostUsers;


    public HomePostDto(String profileImageName, String username, Post post, boolean isPostSaved, boolean isPostLiked, List<CommentDto> comments, String addedAt, boolean isPostUsers) {
        this.profileImageName = profileImageName;
        this.username = username;
        this.post = post;
        this.isPostSaved = isPostSaved;
        this.isPostLiked = isPostLiked;
        this.comments = comments;
        this.addedAt = addedAt;
        this.isPostUsers = isPostUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean getIsPostSaved() {
        return isPostSaved;
    }

    public void setIsPostSaved(boolean postSaved) {
        isPostSaved = postSaved;
    }

    public boolean getIsPostLiked() {
        return isPostLiked;
    }

    public void setIsPostLiked(boolean postLiked) {
        isPostLiked = postLiked;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    public boolean getIsPostUsers() {
        return isPostUsers;
    }

    public void setIsPostUsers(boolean postUsers) {
        isPostUsers = postUsers;
    }
}
