package com.greensense.model.alert;

import javax.swing.DefaultListModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.List;

public class AlertsListModel extends DefaultListModel<AlertModel> {

    private final PropertyChangeSupport support;

    public AlertsListModel(){
        support = new PropertyChangeSupport(this);
    }

    public void loadAlerts(){

        List<AlertModel> alerts = Alerts.getInstance().getAlerts();

        alerts.forEach(this::addElement);

        fireContentsChanged(this, 0, getSize() - 1);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
