package com.greensense.controller;

import com.greensense.model.User;
import com.greensense.model.UserRole;
import com.greensense.model.Users;
import com.greensense.view.components.Form;

import javax.swing.JOptionPane;
import java.util.Map;

public class UserFormHandler implements FormHandler {
    @Override
    public boolean handleFormSubmission(Map<String, Object> formData, Form form) {

        User user = new User(
                (String)formData.get("name"),
                (String)formData.get("surname"),
                (String)formData.get("username"),
                (String)formData.get("password"),
                (UserRole)formData.get("role")
        );

        boolean userExists = Users.getInstance().addUser(user);

        if (userExists){
            JOptionPane.showMessageDialog(form, "Erabiltzailea existitzen da!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(form, "Erabiltzailea arazo gabe sortu da", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        return userExists;

    }

}
