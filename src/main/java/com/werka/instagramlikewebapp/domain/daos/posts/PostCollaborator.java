package com.werka.instagramlikewebapp.domain.daos.posts;

import java.time.LocalDateTime;

public class PostCollaborator {

    private int id;
    private int postId;
    private int userCollaboratorId;
    private LocalDateTime date;

    public PostCollaborator(int id, int postId, int userCollaboratorId, LocalDateTime date) {
        this.id = id;
        this.postId = postId;
        this.userCollaboratorId = userCollaboratorId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserCollaboratorId() {
        return userCollaboratorId;
    }

    public void setUserCollaboratorId(int userCollaboratorId) {
        this.userCollaboratorId = userCollaboratorId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostCollaborator{" +
                "id=" + id +
                ", postId=" + postId +
                ", userCollaboratorId=" + userCollaboratorId +
                ", date=" + date +
                '}';
    }
}
