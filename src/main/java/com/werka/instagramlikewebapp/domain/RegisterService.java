package com.werka.instagramlikewebapp.domain;

import com.werka.instagramlikewebapp.domain.daos.user.UserDao;

public class RegisterService {

    private UserDao userDao = new UserDao();

    public boolean register(String email, String password, String username, String firstName, String lastName, Integer age) {

        if(userDao.isEmailFree(email)) {
            userDao.saveUser(email, password, username, firstName, lastName, age);
            return true;
        }
        return false;
    }

}
