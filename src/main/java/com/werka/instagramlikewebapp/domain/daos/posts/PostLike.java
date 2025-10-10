package com.werka.instagramlikewebapp.domain.daos.posts;

import java.time.LocalDateTime;

public class PostLike {

    private int id;
    private int userId;
    private int postId;
    private LocalDateTime date;

    public PostLike(int id, int userId, int post_id, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.postId = post_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", date=" + date +
                '}';
    }
}
