package com.greensense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private int id;

    @JsonProperty("role")
    private int role;

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

    public String getRoleAsString(){
        
        if (this.role == 1) {
            return "Admin";
        }

        return "User";

    }

}
