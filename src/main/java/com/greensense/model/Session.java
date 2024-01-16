package com.greensense.model;

import java.util.List;


import com.greensense.view.screens.ScreenManager;

public class Session {

    private static Session session;
    private User loggedInUser;

    private Session(){

        loggedInUser = null;

    }

    public static Session getInstance() {

        if (session == null) {
            session = new Session();
        }

        return session;

    }

    public boolean isSet(){
        return loggedInUser == null;
    }

    public boolean login(String username, String password){

        Users users = Users.getInstance();

        List<User> filteredUsers = users.filter(
                        user -> user.getUsername().equals(username) &&
                                user.getPassword().equals(password)
                        );

        if (filteredUsers.size() == 1) {

            loggedInUser = filteredUsers.get(0);

            return true;

        }

        return false;

    }

    public void logout(){

        loggedInUser = null;

        ScreenManager screenManager = ScreenManager.getInstance();

        screenManager.showScreen("login");

    }

    public User getUser() {
        return loggedInUser;
    }

}
