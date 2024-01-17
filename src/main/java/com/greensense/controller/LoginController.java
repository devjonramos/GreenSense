package com.greensense.controller;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.greensense.constants.Constants;
import com.greensense.model.Session;
import com.greensense.view.screens.GreenhouseCardsScreen;
import com.greensense.view.screens.HomepageScreen;
import com.greensense.view.screens.LoginScreen;
import com.greensense.view.screens.ScreenManager;
import com.greensense.view.screens.UsersScreen;

public class LoginController implements Constants, ActionListener {

    private ScreenManager screenManager = ScreenManager.getInstance();
    private LoginScreen login;

    public LoginController(LoginScreen login){
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        // if (e.getSource() instanceof JButton) {
        //     JButton button = (JButton)e.getSource();
        //     System.out.println(button.getAction().getValue(AbstractAction.ACTION_COMMAND_KEY));
        // }
        // else {
        //     System.out.println("no action");
        // }

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_LOAD_HOMEPAGE, () -> {

            screenManager.showScreen("homepage");

        });

        commandHandler.put(PROPERTY_LOGIN, () -> {
            
            String username = login.getUsername();
            String password = login.getPassword();

            Session session = Session.getInstance();

            if (session.login(username, password)) {

                // HomepageScreen homepage = new HomepageScreen();
                // GreenhouseCardsScreen greenhouseCards = new GreenhouseCardsScreen();
                // UsersScreen usersScreen = new UsersScreen();

                // screenManager.addScreen(homepage, "homepage");
                // screenManager.addScreen(greenhouseCards, "greenhouses");
                // screenManager.addScreen(usersScreen, "users");

                screenManager.showScreen("homepage");

            }
            else {
                JOptionPane.showMessageDialog(login, "Erabiltzailea edo pasahitza ez da zuzena", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // if (username.equals("a") && password.equals("b")) {
                
            //     ScreenManager screenManager = ScreenManager.getInstance();

            //     screenManager.showScreen("homepage");

            // }
            // else {
            //     JOptionPane.showMessageDialog(login, "Credenciales invalidas", "Error", JOptionPane.ERROR_MESSAGE);
            // }

        });

        // commandHandler.put(PROPERTY_EXIT, () -> app.dispose() );

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();


    }

}
