package com.werka.instagramlikewebapp.domain.daos.profile;

public class UserProfile {

    private int id;
    private int userId;
    private String username;
    private int postsQuantity;
    private int followers;
    private int following;
    private String bio;

    public UserProfile(int id, int userId, String userName, int postsQuantity, int followers, int following, String bio) {
        this.id = id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
