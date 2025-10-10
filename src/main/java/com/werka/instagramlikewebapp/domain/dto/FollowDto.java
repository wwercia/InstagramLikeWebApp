package com.werka.instagramlikewebapp.domain.dto;

public class FollowDto {

    private int id;
    private String followerUsername;
    private String date;

    public FollowDto(int id, String followerUsername, String date) {
        this.id = id;
        this.followerUsername = followerUsername;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FollowDto{" +
                "id=" + id +
                ", followerUsername='" + followerUsername + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
