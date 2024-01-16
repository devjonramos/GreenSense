package com.greensense.view.screens;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class ScreenManager {
 
    private static ScreenManager instance;

    private JPanel mainPanel;
    private CardLayout layout;
    private Screen currentScreen;
    private Map<String, Screen> screens;

    private ScreenManager(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.layout = new CardLayout();
        this.mainPanel.setLayout(layout);
        this.screens = new HashMap<>();
    }

    // Public method to get the instance with optional argument
    public static ScreenManager getInstance() {
        return getInstance(null); // Calls the other getInstance method with null argument
    }

    // Public method to get the instance with the JPanel argument
    public static ScreenManager getInstance(JPanel panel) {
        if (instance == null) {
            instance = new ScreenManager(panel);
        }
        return instance;
    }

    public void addScreen(Screen screen, String screenName){

        screens.putIfAbsent(screenName, screen);

        mainPanel.add((Component)screen, screenName);
    }

    public void showNextScreen() {
        layout.next(mainPanel);
    }
    
    public void showScreen(String screenName){

        if (currentScreen != null) {
            currentScreen.dispose();
        }

        currentScreen = screens.get(screenName);

        currentScreen.load();

        layout.show(mainPanel, screenName);

    }

}
