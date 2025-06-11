package com.werka.instagramlikewebapp.domain.services;

import com.werka.instagramlikewebapp.domain.daos.user.User;
import com.werka.instagramlikewebapp.domain.daos.user.UserDao;

public class LoginService {

    private UserDao userDao = new UserDao();

    public User logIn(String email, String password) {
        return userDao.getUserIfDataIsCorrect(email, password);
    }

}
