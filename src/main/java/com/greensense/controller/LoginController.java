package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.greensense.constants.Constants;
import com.greensense.model.Session;
import com.greensense.view.screens.*;

public class LoginController implements Constants, ActionListener {

    private final ScreenManager screenManager = ScreenManager.getInstance();
    private LoginScreen login;

    public LoginController(LoginScreen login){
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_LOAD_HOMEPAGE, () -> {

            screenManager.showScreen("homepage");

        });

        commandHandler.put(PROPERTY_LOGIN, () -> {
            
            String username = login.getUsername();
            String password = login.getPassword();

            Session session = Session.getInstance();

            if (session.login(username, password)) {

                HomepageScreen homepage = new HomepageScreen();
                GreenhouseCardsScreen greenhouseCards = new GreenhouseCardsScreen();
                UsersScreen usersScreen = new UsersScreen();
                AlertsScreen alertsScreen = new AlertsScreen();

                screenManager.addScreen(homepage, "homepage");
                screenManager.addScreen(greenhouseCards, "greenhouses");
                screenManager.addScreen(usersScreen, "users");
                screenManager.addScreen(alertsScreen, "alerts");

                screenManager.showScreen("homepage");

            }
            else {
                JOptionPane.showMessageDialog(login, "Erabiltzailea edo pasahitza ez da zuzena", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        // commandHandler.put(PROPERTY_EXIT, () -> app.dispose() );

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();


    }

}
