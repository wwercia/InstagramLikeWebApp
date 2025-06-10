package com.werka.instagramlikewebapp.domain.daos.posts;

public class Comment {

    private int id;
    private int postId;
    private String username;
    private String comment;

    public Comment(int id, int postId, String username, String content) {
        this.id = id;
        this.postId = postId;
        this.username = username;
        this.comment = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
}
