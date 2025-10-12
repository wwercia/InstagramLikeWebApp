package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.profile.UserProfileDao;
import com.werka.instagramlikewebapp.domain.dto.CommentDto;
import com.werka.instagramlikewebapp.domain.daos.posts.*;
import com.werka.instagramlikewebapp.domain.daos.user.UserDao;
import com.werka.instagramlikewebapp.domain.dto.LikeDto;
import com.werka.instagramlikewebapp.domain.dto.TagDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {

    private final PostDao postDao = new PostDao();
    private final SavedPostDao savedPostDao = new SavedPostDao();
    private final PostLikeDao postLikeDao = new PostLikeDao();
    private final CommentDao commentDao = new CommentDao();
    private final UserDao userDao = new UserDao();
    private final PostCollaboratorDao postCollaboratorDao = new PostCollaboratorDao();
    private final UserProfileDao userProfileDao = new UserProfileDao();

    public String savePostAndCollaborators(int userId, String imageName, String description, String location, int likes, List<String> collaborators, String extension) {
        int postId = postDao.savePost(userId, imageName, description, location, likes, extension);
        String newImageName = "post" + postId;
        postDao.changeImageName(newImageName);
        postDao.changeImageExtension(extension, postId);
        postDao.saveCollaborators(postId, collaborators);
        postDao.increasePostsQuantity(userId);
        return newImageName;
    }

    public void deletePost(int postId) {
        postDao.deletePost(postId);
    }

    public List<Post> getUserPosts(int userID) {
        return postDao.getPostsByUserId(userID);
    }

    public Post getPostByImageName(String imageName) {
        return postDao.getPostByImageName(imageName);
    }

    public List<Post> getUserTaggedPosts(int userId) {
        return postDao.getUserTaggedPostsByUserId(userId);
    }
    public void savePostToUserSavedPosts(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        savedPostDao.savePostToUserSavedPosts(postID, userId);
    }

    public List<Post> getUserSavedPosts(int userId) {
        List<Integer> savedPostIds = savedPostDao.getUserSavedPosts(userId);
        if(savedPostIds.isEmpty()) {
            return new ArrayList<>();
        }
        return postDao.getUserSavedPostsByPostIds(savedPostIds);
    }

    public boolean isPostSavedInUserSavedPosts(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        return savedPostDao.isPostSavedInUserSavedPosts(postID, userId);
    }

    public void removePostFromUserSavedPosts(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        savedPostDao.removePostFromUserSavedPosts(postID);
    }

    public boolean isPostLikedByUser(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        return postLikeDao.isPostLikedByUser(postID, userId);
    }

    public void likePost(String imageName, int userId) {
        int postID = postDao.getPostIdByImageName(imageName);
        postLikeDao.saveLikeToPostLikes(postID, userId);
        postDao.increasePostLikes(postID);
    }

    public void dislikePost(String imageName) {
        int postID = postDao.getPostIdByImageName(imageName);
        postLikeDao.removeLikeFromPostLikes(postID);
        postDao.decreasePostLikes(postID);
    }

    public void addComment(String imageName, String comment, int userId) {
        int postId = postDao.getPostIdByImageName(imageName);
        commentDao.saveComment(postId, comment, userId);
    }

    public void deleteComment(int commentId) {
        commentDao.removeComment(commentId);
    }

    public List<CommentDto> getComments(String imageName, int userId) {
        int postId = postDao.getPostIdByImageName(imageName);
        List<Comment> comments = commentDao.getCommentsByPostId(postId);
        return getCommentDto(userId, comments);
    }

    public List<CommentDto> getCommentsFromLastTwoMonths(int userId) {
        List<Post> posts = postDao.getPostsByUserId(userId);
        List<Integer> ids = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> comments = commentDao.getCommentsByUserPostIdsFromLastTwoMonths(ids, userId);
        return getCommentDto(userId, comments);
    }

    private List<CommentDto> getCommentDto(int userId, List<Comment> comments) {
        List<CommentDto> readyComments = new ArrayList<>();
        for(Comment comment : comments) {
            readyComments.add(new CommentDto(
                    comment.getId(),
                    userDao.getUsernameById(comment.getUserId()),
                    userProfileDao.getProfileImageNameById(comment.getUserId()),
                    comment.getPostId(),
                    comment.getComment(),
                    (comment.getUserId() == userId),
                    String.format("%d %s %d", comment.getAddedAt().getDayOfMonth(), comment.getAddedAt().getMonth().name().toLowerCase(), comment.getAddedAt().getYear())
            ));
        }
        return readyComments;
    }

    public List<LikeDto> getLikesFromLastTwoMonths(int userId) {
        List<Post> posts = postDao.getPostsByUserId(userId);
        List<Integer> ids = posts.stream().map(Post::getId).collect(Collectors.toList());
        List<PostLike> likes = postLikeDao.getLikesFromLastTwoMonths(ids, userId);
        List<LikeDto> readyLikes = new ArrayList<>();
        for(PostLike like : likes) {
            readyLikes.add(new LikeDto(
                    like.getId(),
                    userDao.getUsernameById(like.getUserId()),
                    userProfileDao.getProfileImageNameById(like.getUserId()),
                    like.getPostId(),
                    String.format("%d %s %d", like.getDate().getDayOfMonth(), like.getDate().getMonth().name().toLowerCase(), like.getDate().getYear())
            ));
        }
        return readyLikes;
    }

    public List<TagDto> getTagsFromLastTwoMonths(int userId) {
        List<PostCollaborator> postCollaborators = postCollaboratorDao.getTagsFromLastTwoMonths(userId);
        List<TagDto> readyTags = new ArrayList<>();
        for(PostCollaborator tag : postCollaborators) {
            int id = postDao.getUserIdByPostId(tag.getPostId());
            readyTags.add(new TagDto(
                    tag.getId(),
                    userDao.getUsernameById(id),
                    userProfileDao.getProfileImageNameById(id),
                    tag.getPostId(),
                    String.format("%d %s %d", tag.getDate().getDayOfMonth(), tag.getDate().getMonth().name().toLowerCase(), tag.getDate().getYear())
            ));
        }
        return readyTags;
    }

}
