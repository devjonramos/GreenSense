package com.greensense.model;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Admin"),
    USER("Erabiltzailea");

    private final String name;

    UserRole(String name){
        this.name = name;
    }

    public UserRole fromString(String role) {

        for (UserRole r : UserRole.values()) {
            if(role.equals(r.toString())) return r;
        }

        return null;

    }

}
