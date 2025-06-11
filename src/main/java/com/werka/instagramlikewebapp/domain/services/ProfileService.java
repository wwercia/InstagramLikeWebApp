package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.profile.UserProfile;
import com.werka.instagramlikewebapp.domain.daos.profile.UserProfileDao;
import com.werka.instagramlikewebapp.domain.daos.user.UserDao;

public class ProfileService {

    private UserProfileDao userProfileDao = new UserProfileDao();
    private UserDao userDao = new UserDao();

    public UserProfile getProfileInfo(int userId) {
        return userProfileDao.getUserProfileById(userId, userDao.getUsernameById(userId));
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

}
