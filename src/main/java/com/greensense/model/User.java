package com.greensense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.greensense.constants.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Constants {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private UserRole role;

    @JsonProperty("lastSeen")
    private String lastSeen;

    public User(String name, String surname, String username, String password, UserRole role){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastSeen = capitalize(DATE_FORMAT.format(new Date()));
    }

    public String getFullName(){
        return name + " " + surname;
    }

    public String[] toStringArray(){
        return new String[]{getName(), getSurname(), getRole().getName(), getLastSeen()};
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof User)) return false;

        User other = (User)o;

        return (other.name.equals(this.name) && other.surname.equals(this.surname)) || other.username.equals(this.username);

    }

    @Override
    public int hashCode() {

        int result = 1;
        long id = this.getId();

        Object name = this.getName();
        Object surname = this.getSurname();
        Object username = this.getUsername();
        Object password = this.getPassword();
        Object role = this.getRole();
        Object lastSeen = this.getLastSeen();

        result = result * 59 + (int)(id >>> 32 ^ id);
        result = result * 59 + (name == null ? 43 : name.hashCode());
        result = result * 59 + (surname == null ? 43 : surname.hashCode());
        result = result * 59 + (username == null ? 43 : username.hashCode());
        result = result * 59 + (password == null ? 43 : password.hashCode());
        result = result * 59 + (role == null ? 43 : role.hashCode());
        result = result * 59 + (lastSeen == null ? 43 : lastSeen.hashCode());

        return result;
    }

}