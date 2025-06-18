package com.werka.instagramlikewebapp.domain.api;

public class CommentDto {

    private int id;
    private String username;
    private String comment;
    private boolean commentMine;

    public CommentDto(int id, String username, String comment, boolean commentMine) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.commentMine = commentMine;
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
}
