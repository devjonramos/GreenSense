package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import com.greensense.constants.Constants;
import com.greensense.model.GreenhouseModel;
import com.greensense.view.screens.GreenhouseCardsScreen;
import com.greensense.view.screens.GreenhouseScreen;
import com.greensense.view.screens.ScreenManager;

public class GreenhouseCardsController implements Constants, ActionListener {

    private ScreenManager screenManager = ScreenManager.getInstance();
    private GreenhouseCardsScreen view;

    public GreenhouseCardsController(GreenhouseCardsScreen view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_GO_BACK, () -> {

            screenManager.showScreen("homepage");

        });

        commandHandler.put(PROPERTY_SEARCH_GREENHOUSES, () -> {

            if (e.getSource() instanceof JButton) {
                
                JButton button = (JButton)e.getSource();

                Thread thread = new Thread(() -> {
                    System.out.println(button.getSize());
                });

                thread.start();

            }

        });

        commandHandler.put(PROPERTY_OPEN_GREENHOUSE, () -> {

            if (e.getSource() instanceof JButton) {

                JButton button = (JButton)e.getSource();

                GreenhouseModel greenhouse = (GreenhouseModel)button.getAction().getValue("greenhouse");
                GreenhouseScreen greenhouseScreen = view.getGreenhouseScreen();

                if (greenhouseScreen == null) {
                    
                    greenhouseScreen = new GreenhouseScreen(greenhouse);

                    view.setGreenhouseScreen(greenhouseScreen);

                    screenManager.addScreen(greenhouseScreen, "greenhouse");

                }

                greenhouseScreen.setGreenhouseModel(greenhouse);

                // greenhouseScreen.updateCO2(
                //     Integer.toString(
                //         greenhouse.getCO2level()
                //     )
                // );

                screenManager.showScreen("greenhouse");

                System.out.println(greenhouse.getId());

            }

            // ScreenManager screenManager = ScreenManager.getInstance();

            // screenManager.showScreen("view");

        });

        commandHandler.put(PROPERTY_CHANGE_GREENHOUSE_NAME, () -> {
            
            if (e.getSource() instanceof JButton) {

                System.out.println("change name");

                JButton button = (JButton)e.getSource();

                GreenhouseModel greenhouseModel = (GreenhouseModel)button.getAction().getValue("greenhouse");

                greenhouseModel.setName("Name changed");

            }

            // ScreenManager screenManager = ScreenManager.getInstance();

            // screenManager.showScreen("view");

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

}
