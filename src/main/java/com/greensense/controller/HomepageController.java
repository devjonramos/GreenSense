package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.greensense.constants.Constants;
import com.greensense.model.Session;
import com.greensense.view.screens.HomepageScreen;
import com.greensense.view.screens.ScreenManager;

public class HomepageController implements Constants, ActionListener {

    private ScreenManager screenManager = ScreenManager.getInstance();
    private HomepageScreen homepage;
    
    public HomepageController(HomepageScreen homepage) {
        this.homepage = homepage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_LOAD_PAGE_GREENHOUSES, () -> {

            screenManager.showScreen("greenhouses");

        });

        commandHandler.put(PROPERTY_LOAD_PAGE_ALERTS, () -> {
            
            

        });

        commandHandler.put(PROPERTY_LOAD_PAGE_ANALYTICS, () -> {
            
            

        });

        commandHandler.put(PROPERTY_LOAD_PAGE_USERS, () -> {
            
            Session session = Session.getInstance();

            if (session.getUser().getRole() == 1) {

                screenManager.showScreen("users");

            }
            else {
                JOptionPane.showMessageDialog(homepage, "Ez daukazu baimenik!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        commandHandler.put(PROPERTY_LOAD_PAGE_SETTINGS, () -> {
            
            

        });

        // commandHandler.put(PROPERTY_EXIT, () -> app.dispose() );

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();


    }

}
