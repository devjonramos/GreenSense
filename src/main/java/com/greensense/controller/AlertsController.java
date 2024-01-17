package com.greensense.controller;

import com.greensense.constants.Constants;
import com.greensense.view.screens.AlertsScreen;
import com.greensense.view.screens.ScreenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsController implements Constants, ActionListener {

    private ScreenManager screenManager = ScreenManager.getInstance();

    private AlertsScreen view;

    public AlertsController(AlertsScreen alertsScreen){
        this.view = alertsScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
