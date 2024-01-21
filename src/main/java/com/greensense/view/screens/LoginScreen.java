package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import java.beans.PropertyChangeEvent;

import com.greensense.Palette;
import com.greensense.controller.LoginController;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;

public class LoginScreen extends JPanel implements Screen {

    private LoginController controller;

    private AbstractAction actionLogin;

    private JTextField usernameField = ComponentFactory.createTextField(JTextField.class);
    private JTextField passwordField = ComponentFactory.createTextField(JPasswordField.class);

    public LoginScreen() {

        super(new BorderLayout(0, 0));

        this.controller = new LoginController(this);
        this.createActions();

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        setBackground(Palette.MAIN_BG);

    }

    private void createActions() {

        actionLogin = ActionBuilder.createAction("Login", PROPERTY_LOGIN, controller).build();

    }

    private JPanel createLeftPanel(){

        JPanel panel = new JPanel(new BorderLayout()){

            {

                JLabel image = new JLabel(IMG_GREENHOUSE_BG);
                image.setPreferredSize(new Dimension(514, 860));

                add(image, BorderLayout.CENTER);

            }

        };

        return panel;

    }

    private JPanel createRightPanel(){
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Palette.MAIN_BG);

        JToolBar header = createHeaderPanel();

        JPanel content = new JPanel(){

            {

                BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

                JPanel loginForm = createLoginForm();

                setLayout(layout);
                setOpaque(false);
                add(Box.createVerticalGlue());
                add(loginForm);
                add(Box.createVerticalGlue());

            }

        };
        

        panel.add(header, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);

        return panel;

    }

    private JToolBar createHeaderPanel(){

        JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
        toolBar.setBorder(BorderCreator.createEmptyBorder(48, 24));

        JLabel logo = ComponentFactory.createLabel("GreenSense", Palette.LOGO_BG, PoppinsSemiBold_24);

        toolBar.add(logo);

        return toolBar;

    }

    private JPanel createLoginForm() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 44, 0, 44));

        JLabel heading = ComponentFactory.createLabel("Hasi Saioa", Palette.TEXT_PRIMARY_FG, InterSemiBold_24);
        JTextArea subheading = ComponentFactory.createTextArea("Sartu ezazu zure erabiltzailea eta pasahitza saioa hasteko", Palette.TEXT_SECONDARY_FG, InterRegular_16);
        
        JPanel username = ComponentFactory.createInputGroup(usernameField, "Erabiltzailea");
        JPanel password = ComponentFactory.createInputGroup(passwordField, "Pasahitza");

        JButton btnLogin = ComponentFactory.createPrimaryButton(actionLogin, ButtonSize.LARGE);

        GroupLayout layout = new GroupLayout(panel);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(heading)
            .addComponent(subheading)
            .addComponent(username)
            .addComponent(password)
            .addComponent(btnLogin)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(heading, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(8)
            .addComponent(subheading, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(32)
            .addComponent(username)
            .addGap(32)
            .addComponent(password)
            .addGap(32)
            .addComponent(btnLogin)
        );

        panel.setLayout(layout);

        return panel;

    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {



    }

    @Override
    public void load() {
        
    }

    @Override
    public void dispose() {
        
    }


}
