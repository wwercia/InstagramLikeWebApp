package com.werka.instagramlikewebapp.domain.daos.posts;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private int postId;
    private int userId;
    private String comment;
    private LocalDateTime addedAt;

    public Comment(int id, int postId, int userId, String content, LocalDateTime addedAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.comment = content;
        this.addedAt = addedAt;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }
}
