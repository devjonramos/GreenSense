package com.greensense.model.alert;

import com.greensense.constants.Constants;

import javax.swing.DefaultListModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.List;

public class AlertsListModel extends DefaultListModel<AlertModel> implements Constants {

    private final PropertyChangeSupport support;

    public AlertsListModel(){
        support = new PropertyChangeSupport(this);
    }

    public void loadAlerts(){

        clear();

        List<AlertModel> alerts = Alerts.getInstance().getAlerts();

        alerts.forEach(this::addElement);

        fireContentsChanged(this, 0, getSize() - 1);

        support.firePropertyChange(PROPERTY_UPDATE_ALERTS_LIST, null, null);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
