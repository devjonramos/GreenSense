package com.greensense.constants;

import com.greensense.model.Session;
import com.greensense.view.screens.ScreenManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public interface Constants {

    // LoginScreen
    String PROPERTY_LOGIN = "Login to account";
    String PROPERTY_LOAD_HOMEPAGE = "Load Homepage";


    // HomepageScreen
    String PROPERTY_LOAD_SCREEN_GREENHOUSES = "Load GreenhouseCards Screen";
    String PROPERTY_LOAD_SCREEN_ALERTS = "Load Alerts Screen";
    String PROPERTY_LOAD_SCREEN_ANALYTICS = "Load Analytics Screen";
    String PROPERTY_LOAD_SCREEN_USERS = "Load Users Screen";
    String PROPERTY_LOAD_SCREEN_SETTINGS = "Load Settings Screen";


    // GreenhouseCardsScreen
    String PROPERTY_SEARCH_GREENHOUSES = "Search Greenhouses By Name";
    String PROPERTY_OPEN_GREENHOUSE = "Open Greenhouse";
    String PROPERTY_CHANGE_GREENHOUSE_NAME = "Change Greenhouse's Name";


    // GreenhouseScreen
    String PROPERTY_NEXT_GREENHOUSE = "Load next greenhouse screen";
    String PROPERTY_PREVIOUS_GREENHOUSE = "Load previous greenhouse screen";
    String PROPERTY_TOGGLE_MODE = "Toggle greenhouse's mode";
    String PROPERTY_TOGGLE_FAN1 = "Toggle greenhouse's fan 1 state";
    String PROPERTY_TOGGLE_FAN2 = "Toggle greenhouse's fan 2 state";
    String PROPERTY_UPDATE_CO2 = "Update CO2 level";


    // Deprecated
    @Deprecated String PROPERTY_EXIT = "exit";
    @Deprecated String PROPERTY_LOAD_MENU = "Load Menu";
    @Deprecated String PROPERTY_GO_BACK = "Return to previous screen";
    @Deprecated String PROPERTY_UPDATE_GREENHOUSE_MODEL = "Update greenhouse model";


    // Actions used across all the app
    AbstractAction ACTION_LOGOUT = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Session session = Session.getInstance();

            session.logout();

        }

    };

    AbstractAction ACTION_BACK = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            ScreenManager screenManager = ScreenManager.getInstance();

            screenManager.goBack();

        }

    };

    // MQTT Constants
    String MQTT_BROKER = "tcp://192.168.1.100";
    int QoS0 = 0;
    int QoS1 = 1;
    int QoS2 = 2;

}
