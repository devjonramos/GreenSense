package com.greensense.controller;

import com.greensense.constants.Constants;
import com.greensense.model.alert.AlertsListModel;
import com.greensense.view.screens.AlertsScreen;
import com.greensense.view.screens.ScreenManager;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsController implements Constants, ActionListener, ListSelectionListener {

    private final ScreenManager screenManager = ScreenManager.getInstance();

    private AlertsScreen view;
    private AlertsListModel model;

    public AlertsController(AlertsScreen alertsScreen, AlertsListModel model){
        this.view = alertsScreen;
        this.model = model;
        this.model.addPropertyChangeListener(view);
    }

    public void loadAlerts(){
        model.loadAlerts();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
