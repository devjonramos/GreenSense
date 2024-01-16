package com.greensense.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.util.JSONManager;

public class Users {

    private static Users instance;
    private List<User> users;

    private JSONManager jsonManager;

    private Users(){

        jsonManager = new JSONManager();

        try {
            
            users = jsonManager.loadJSON("json/users.json", new TypeReference<List<User>>(){});

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

    public List<User> getUsers(){
        return new ArrayList<>(users);
    }

    public List<User> filter(Predicate<? super User> predicate){

        return getUsers().stream().filter(predicate).collect(Collectors.toList());

    }

}
