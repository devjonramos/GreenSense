package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greensense.constants.Constants;
import com.greensense.model.User;
import com.greensense.model.Users;
import com.greensense.view.components.Form;
import com.greensense.view.components.FormElement;
import com.greensense.view.screens.ScreenManager;
import com.greensense.view.screens.UsersScreen;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UsersController implements Constants, ActionListener {

    private final ScreenManager screenManager = ScreenManager.getInstance();
    private UsersScreen usersScreen;

    public UsersController(UsersScreen usersScreen) {
        this.usersScreen = usersScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_ADD_USER, () -> {

            if (e.getSource() instanceof JButton) {

                JButton button = (JButton)e.getSource();

                Form form = new Form(
                        screenManager.getFrame(), "Gehitu Erabiltzailea", true,
                        new FormElement.Builder( "name","Izena").build(),
                        new FormElement.Builder( "surname","Abizena").build(),
                        new FormElement.Builder( "username","Erabiltzailea").build(),
                        new FormElement.Builder( "password","Pasahitza").build(),
                        new FormElement.Builder( "role","Rola").withComboBox(new String[]{"aaa","bbb","ccc"}).build()
                );

                if (form.wasSubmitted()) {

                    Map<String, Object> formData = form.getData();

                    Users.getInstance().addUser(
                            new User(
                                    (String)formData.get("name"),
                                    (String)formData.get("surname"),
                                    (String)formData.get("username"),
                                    (String)formData.get("password"),
                                    (int)formData.get("role")
                            )
                    );

                }

            }

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

}
