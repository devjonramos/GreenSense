package com.greensense;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.greensense.view.screens.*;

public class Main {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("GreenSense");

            JPanel mainPanel = new JPanel() {{ setBackground(Color.WHITE);}};
            ScreenManager screenManager = ScreenManager.getInstance(mainPanel);

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

        });

    }

}
