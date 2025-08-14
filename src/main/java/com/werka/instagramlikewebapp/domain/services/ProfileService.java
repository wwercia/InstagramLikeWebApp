package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.api.FollowerDto;
import com.werka.instagramlikewebapp.domain.daos.profile.UserFollowDao;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfileDao;
import com.werka.instagramlikewebapp.domain.daos.user.UserDao;

import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    private UserProfileDao userProfileDao = new UserProfileDao();
    private UserDao userDao = new UserDao();
    private UserFollowDao userFollowDao = new UserFollowDao();

    public UserProfile getProfileInfo(int userId) {
        return userProfileDao.getUserProfileById(userId, userDao.getUsernameById(userId));
    }

    public UserProfile getProfileInfoByUsername(String username) {
        int userId = userDao.getUserIdByUserName(username);
        return userProfileDao.getUserProfileById(userId, username);
    }

    public void updateBio(String bio, int userId) {
        userProfileDao.updateBio(bio, userId);
    }

    public void updateUsername(String username, int userId) {
        userDao.updateUsername(username, userId);
    }

    public void saveNewProfileImage(String imageName, int userId) {
        userProfileDao.saveNewProfileImage(imageName, userId);
    }

    public boolean isUserFollowed(int followerId, int followedId) {
        return userFollowDao.isUserFollowed(followerId, followedId);
    }

    public void follow(int followerId, int followedId) {
        userFollowDao.follow(followerId, followedId);
        userProfileDao.increaseFollowersQuantity(followedId);
        userProfileDao.increaseFollowingQuantity(followerId);
    }

    public void unfollow(int followerId, int followedId) {
        userFollowDao.unfollow(followerId, followedId);
        userProfileDao.decreaseFollowersQuantity(followedId);
        userProfileDao.decreaseFollowingQuantity(followerId);
    }

    public List<FollowerDto> getFollowers(int userId) {
        return userFollowDao.getFollowersFullData(userId);
    }

    public List<FollowerDto> getFollowing(int userId) {
        return userFollowDao.getFollowingFullData(userId);
    }
}
