package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greensense.constants.Constants;
import com.greensense.model.User;
import com.greensense.model.UserRole;
import com.greensense.model.Users;
import com.greensense.model.UsersTableModel;
import com.greensense.view.components.Form;
import com.greensense.view.components.FormElement;
import com.greensense.view.screens.ScreenManager;
import com.greensense.view.screens.UsersScreen;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class UsersController implements Constants, ActionListener {

    private final ScreenManager screenManager = ScreenManager.getInstance();
    private UsersScreen usersScreen;
    private UsersTableModel model;

    public UsersController(UsersScreen usersScreen, UsersTableModel model) {
        this.usersScreen = usersScreen;
        this.model = model;
        this.model.addPropertyChangeListener(this.usersScreen);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_ADD_USER, () -> {

            if (e.getSource() instanceof JButton) {

                JButton button = (JButton)e.getSource();

                Form form = new Form(
                        screenManager.getFrame(), "Gehitu Erabiltzailea", true, new UserFormHandler(),
                        new FormElement.Builder( "name","Izena").build(),
                        new FormElement.Builder( "surname","Abizena").build(),
                        new FormElement.Builder( "username","Erabiltzailea").build(),
                        new FormElement.Builder( "password","Pasahitza").build(),
                        new FormElement.Builder( "role","Rola").withComboBox(UserRole.values()).build()
                );

                if(form.wasSubmitted()) model.loadData();

            }

        });

        commandHandler.put(PROPERTY_DELETE_USER, () -> {

            JTable table = usersScreen.getUsersTable();

            int selectedRow = table.getSelectedRow();

            Object[] rowData = model.getRow(selectedRow);

            String username = (String) rowData[0];

            User user = Users.getInstance().getUserByUsername(username);

            boolean removed = Users.getInstance().removeUser(user);

            if(removed) model.loadData();

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

}
