package com.greensense;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.greensense.model.Greenhouses;
import com.greensense.model.Users;
import com.greensense.model.alert.Alerts;
import com.greensense.view.screens.LoginScreen;
import com.greensense.view.screens.ScreenManager;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Load data used across all the app
            Alerts.getInstance();
            Users.getInstance();
            Greenhouses.getInstance();

            // Create frame and initialize the ScreenManager class
            JFrame frame = new JFrame("GreenSense");
            JPanel mainPanel = new JPanel() {{ setBackground(Color.WHITE); }};
            ScreenManager screenManager = ScreenManager.getInstance(frame, mainPanel);

            LoginScreen login = new LoginScreen();

            screenManager.addScreen(login, "login");
            screenManager.showScreen("login");

            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setSize(1028, 860);
            // this.pack() // For a dynamic window size
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowListener() {

                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("Window closing");
                    Alerts.getInstance().save();
                }

                @Override
                public void windowOpened(WindowEvent e) {

                }
                @Override
                public void windowClosed(WindowEvent e) {

                }
                @Override
                public void windowIconified(WindowEvent e) {

                }
                @Override
                public void windowDeiconified(WindowEvent e) {

                }
                @Override
                public void windowActivated(WindowEvent e) {

                }
                @Override
                public void windowDeactivated(WindowEvent e) {

                }

            });

        });

    }

}
