package com.werka.instagramlikewebapp.domain.daos.profile;

public class UserFollow {

    private int id;
    private int followerId;
    private int followedId;

    public UserFollow(int id, int followerId, int followedId) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowedId() {
        return followedId;
    }

    public void setFollowedId(int followedId) {
        this.followedId = followedId;
    }
}
