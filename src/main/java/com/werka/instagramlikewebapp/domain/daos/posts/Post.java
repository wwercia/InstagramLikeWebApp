package com.werka.instagramlikewebapp.domain.daos.posts;

import java.util.List;

public class Post {

    private int id;
    private int userId;
    private String imageName;
    private String description;
    private String location;
    private int likes;

    public Post(int id, int userId, String imageName, String description, String location, int likes) {
        this.id = id;
        this.userId = userId;
        this.imageName = imageName;
        this.location = location;
        this.description = description;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
