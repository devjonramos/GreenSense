package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import com.greensense.constants.Constants;
import com.greensense.view.screens.ScreenManager;
import com.greensense.view.screens.UsersScreen;

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



        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

}
