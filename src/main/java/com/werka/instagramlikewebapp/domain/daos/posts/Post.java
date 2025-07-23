package com.werka.instagramlikewebapp.domain.daos.posts;

import java.time.LocalDateTime;

public class Post {

    private int id;
    private int userId;
    private String imageName;
    private String description;
    private String location;
    private int likes;
    private LocalDateTime addedAt;
    private String extension;

    public Post(int id, int userId, String imageName, String description, String location, int likes, LocalDateTime addedAt, String extension) {
        this.id = id;
        this.userId = userId;
        this.imageName = imageName;
        this.location = location;
        this.description = description;
        this.likes = likes;
        this.addedAt = addedAt;
        this.extension = extension;
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

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", imageName='" + imageName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", likes=" + likes +
                ", addedAt=" + addedAt +
                ", extension='" + extension + '\'' +
                '}';
    }
}
