package com.werka.instagramlikewebapp.domain.dto;

public class TagDto {

    private int id;
    private String username;
    private String profileImageName;
    private int postId;
    private String date;

    public TagDto(int id, String username, String profileImageName, int postId, String date) {
        this.id = id;
        this.username = username;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "TaggedDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", postId=" + postId +
                '}';
    }
}
