package com.greensense;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.greensense.view.screens.GreenhouseScreen;
import com.greensense.view.screens.GreenhouseCardsScreen;
import com.greensense.view.screens.HomepageScreen;
import com.greensense.view.screens.LoginScreen;
import com.greensense.view.screens.ScreenManager;
import com.greensense.view.screens.UsersScreen;

public class Main {

    public Main(){

    }

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("GreenSense");

            JPanel mainPanel = new JPanel() {{ setBackground(Color.WHITE);}};
            ScreenManager screenManager = ScreenManager.getInstance(mainPanel);

            LoginScreen login = new LoginScreen();
            HomepageScreen homepage = new HomepageScreen();
            GreenhouseCardsScreen greenhouseCards = new GreenhouseCardsScreen();
            UsersScreen usersScreen = new UsersScreen();
            // GreenhouseScreen greenhouse = new GreenhouseScreen();

            screenManager.addScreen(login, "login");
            screenManager.addScreen(homepage, "homepage");
            screenManager.addScreen(greenhouseCards, "greenhouses");
            screenManager.addScreen(usersScreen, "users");
            // screenManager.addScreen(greenhouse, "greenhouse");

            screenManager.showScreen("login");

            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setSize(1028, 860);
            // this.pack() // For a dynamic window size
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        });

    }

}
