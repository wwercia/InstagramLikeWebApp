package com.werka.instagramlikewebapp.domain.daos.profile;

import java.time.LocalDateTime;

public class UserFollow {

    private int id;
    private int followerId;
    private int followedId;
    private LocalDateTime date;

    public UserFollow(int id, int followerId, int followedId, LocalDateTime date) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
