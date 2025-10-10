package com.werka.instagramlikewebapp.domain.dto;

public class ProfileInfo {

    private int id;
    private String username;
    private int postsQuantity;
    private int followers;
    private int following;
    private String bio;

    public ProfileInfo(int id, String userName, int postsQuantity, int followers, int following, String bio) {
        this.id = id;
        this.username = userName;
        this.postsQuantity = postsQuantity;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
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

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getPostsQuantity() {
        return postsQuantity;
    }

    public void setPostsQuantity(int postsQuantity) {
        this.postsQuantity = postsQuantity;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", postsQuantity=" + postsQuantity +
                ", followers=" + followers +
                ", following=" + following +
                ", bio='" + bio + '\'' +
                '}';
    }
}
