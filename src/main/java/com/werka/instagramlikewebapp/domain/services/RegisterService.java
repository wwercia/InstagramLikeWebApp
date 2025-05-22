package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.profile.UserProfileDao;
import com.werka.instagramlikewebapp.domain.daos.user.UserDao;

public class RegisterService {

    private UserDao userDao = new UserDao();
    private UserProfileDao userProfileDao = new UserProfileDao();

    public boolean register(String email, String password, String username, String firstName, String lastName, Integer age) {
        if(userDao.isEmailFree(email)) {
            int id = userDao.saveUser(email, password, username, firstName, lastName, age);
            userProfileDao.saveNewProfile(id);
            return true;
        }
        return false;
    }

}
