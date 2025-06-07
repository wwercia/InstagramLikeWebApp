package com.werka.instagramlikewebapp.domain.daos.posts;

public class PostLike {

    private int id;
    private int userId;
    private int post_id;

    public PostLike(int id, int userId, int post_id) {
        this.id = id;
        this.userId = userId;
        this.post_id = post_id;
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

}
