package com.werka.instagramlikewebapp.domain.dto;

public class LikeDto {

    private int id;
    private String username;
    private String profileImageName;
    private int postId;
    private String date;

    public LikeDto(int id, String followerUsername, String profileImageName, int postId, String date) {
        this.id = id;
        this.username = followerUsername;
        this.profileImageName = profileImageName;
        this.postId = postId;
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
                ", username='" + username + '\'' +
                ", profileImageName='" + profileImageName + '\'' +
                ", postId=" + postId +
                ", date='" + date + '\'' +
                '}';
    }
}
