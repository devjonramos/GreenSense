package com.greensense.controller;

import com.greensense.constants.Constants;
import com.greensense.view.screens.AlertsScreen;
import com.greensense.view.screens.ScreenManager;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsController implements Constants, ActionListener, ListSelectionListener {

    private final ScreenManager screenManager = ScreenManager.getInstance();

    private AlertsScreen view;

    public AlertsController(AlertsScreen alertsScreen){
        this.view = alertsScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
