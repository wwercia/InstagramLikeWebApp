package com.werka.instagramlikewebapp.domain;

import com.werka.instagramlikewebapp.domain.daos.user.UserDao;

public class LoginService {

    private UserDao userDao = new UserDao();

    public boolean logIn(String email, String password) {

        if(userDao.isDataCorrect(email, password)){
            return true;
        }
        return false;
    }

}
