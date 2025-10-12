package com.werka.instagramlikewebapp.domain.dto;

public class CommentDto {

    private int id;
    private String username;
    private String profileImageName;
    private int postId;
    private String comment;
    private boolean commentMine;
    private String date;

    public CommentDto(int id, String username, String profileImageName, int postId, String comment, boolean commentMine, String date) {
        this.id = id;
        this.username = username;
        this.profileImageName = profileImageName;
        this.postId = postId;
        this.comment = comment;
        this.commentMine = commentMine;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCommentMine() {
        return commentMine;
    }

    public void setCommentMine(boolean commentMine) {
        this.commentMine = commentMine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
