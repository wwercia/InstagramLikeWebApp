package com.werka.instagramlikewebapp.domain.dto;

public class LikeDto {

    private int id;
    private String username;
    private int postId;
    private String date;

    public LikeDto(int id, String followerUsername, int postId, String date) {
        this.id = id;
        this.username = followerUsername;
        this.postId = postId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFollowerUsername() {
        return username;
    }

    public void setFollowerUsername(String followerUsername) {
        this.username = followerUsername;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LikeDto{" +
                "id=" + id +
                ", followerUsername='" + username + '\'' +
                ", postId=" + postId +
                ", date='" + date + '\'' +
                '}';
    }
}
