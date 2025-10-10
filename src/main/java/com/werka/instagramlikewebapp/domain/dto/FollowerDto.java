package com.werka.instagramlikewebapp.domain.dto;

public class FollowerDto {

    private int id;
    private String username;
    private String profileImageName;

    public FollowerDto(int id, String username, String profileImageName) {
        this.id = id;
        this.username = username;
        this.profileImageName = profileImageName;
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

    @Override
    public String toString() {
        return "FollowerDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", profileImageName='" + profileImageName + '\'' +
                '}';
    }
}
