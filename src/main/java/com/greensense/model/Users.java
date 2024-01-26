package com.greensense.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.constants.Constants;
import com.greensense.util.json.JSONManager;

public class Users implements Constants {

    private static Users instance;

    private List<User> users;

    private Users(){

        try {
            
            users = JSONManager.loadJSON(JSONManager.USERS_FILE, new TypeReference<List<User>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Users getInstance() {

        if (instance == null) {
            instance = new Users();
        }

        return instance;

    }

    public boolean addUser(User user){

        if (!userExists(user)){
            return users.add(user);
        }

        return false;

    }

    public boolean userExists(User other){

        for (User user : users) {
            if (user.equals(other)) return true;
        }

        return false;

    }

    public void save(){

        try {

            JSONManager.writeToJSON(JSONManager.USERS_FILE, users);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getUsers(){
        return new ArrayList<>(users);
    }

    public List<User> filter(Predicate<? super User> predicate){

        return getUsers().stream().filter(predicate).collect(Collectors.toList());

    }

}
