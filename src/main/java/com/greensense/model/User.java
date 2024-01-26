package com.greensense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("lastSeen")
    private String lastSeen;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private int role;

    public User(String name, String surname, String username, String password, int role){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.surname = surname;
        this.lastSeen = "";
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getRoleAsString(){
        
        if (this.role == 1) {
            return "Admin";
        }

        return "Erabiltzailea";

    }

}