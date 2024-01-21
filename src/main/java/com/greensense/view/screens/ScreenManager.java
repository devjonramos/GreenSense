package com.greensense.view.screens;

import java.awt.CardLayout;
import java.awt.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;

public class ScreenManager {
 
    private static ScreenManager instance;

    private final JPanel mainPanel;
    private final CardLayout layout;
    private Screen currentScreen;
    private LinkedList<Screen> screenHistory;
    private Map<String, Screen> screens;

    private ScreenManager(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.layout = new CardLayout();
        this.mainPanel.setLayout(layout);
        this.screens = new HashMap<>();
        this.screenHistory = new LinkedList<>();
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

    public Screen getScreen(String screenName) { return screens.getOrDefault(screenName, null); }

    private String getScreenNameFromScreen(Screen screen){

        for (Map.Entry<String, Screen> entry : screens.entrySet()) {

            if (screen.equals(entry.getValue())) {
                return entry.getKey();
            }

        }

        return null;

    }

    public void goBack(){

        if (screenHistory.size() < 2) return;

        screenHistory.removeFirst();

        Screen screen = screenHistory.getFirst();

        if (screen != null){

            String screenName = getScreenNameFromScreen(screen);

            showScreen(screenName);

        }

    }

    public void reloadCurrentScreen(){
        currentScreen.dispose();
        currentScreen.load();
    }

    public void showScreen(String screenName){

        if (currentScreen != null) {
            currentScreen.dispose();
        }

        currentScreen = screens.get(screenName);

        currentScreen.load();

        if (!screenHistory.contains(currentScreen)) {
            screenHistory.addFirst(currentScreen);
        }

        layout.show(mainPanel, screenName);

    }

}
