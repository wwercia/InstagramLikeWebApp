package com.werka.instagramlikewebapp.config;

import com.werka.instagramlikewebapp.domain.daos.user.User;

public class DataHelper {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User userr) {
        user = userr;
    }

}
