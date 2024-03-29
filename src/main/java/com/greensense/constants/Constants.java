package com.greensense.constants;

import com.greensense.model.Session;
import com.greensense.view.screens.ScreenManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Constants {

    // General use properties
    String PROPERTY_CONFIRM = "confirm";
    String PROPERTY_CANCEL = "cancel";

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
    String PROPERTY_DELETE_GREENHOUSE = "Delete greenhouse";
    String PROPERTY_UPDATE_GREENHOUSES = "Update greenhouses";
    String PROPERTY_ADD_GREENHOUSE = "Add new greenhouse";


    // GreenhouseScreen
    String PROPERTY_NEXT_GREENHOUSE = "Load next greenhouse screen";
    String PROPERTY_PREVIOUS_GREENHOUSE = "Load previous greenhouse screen";
    String PROPERTY_TOGGLE_MODE = "Toggle greenhouse's mode";
    String PROPERTY_TOGGLE_FAN1 = "Toggle greenhouse's fan 1 state";
    String PROPERTY_TOGGLE_FAN2 = "Toggle greenhouse's fan 2 state";
    String PROPERTY_UPDATE_GREENHOUSE_NAME = "Update greenhouse name";
    String PROPERTY_UPDATE_GREENHOUSE_MODE = "Update greenhouse mode";
    String PROPERTY_UPDATE_GREENHOUSE_FAN_1 = "Update greenhouse fan 1";
    String PROPERTY_UPDATE_GREENHOUSE_FAN_2 = "Update greenhouse fan 2";
    String PROPERTY_UPDATE_GREENHOUSE_PPM = "Update PPM value";


    // UsersScreen
    String PROPERTY_ADD_USER = "Add new user";

    String PROPERTY_DELETE_USER = "Delete user";
    String PROPERTY_UPDATE_USERS_TABLE = "Update users table";


    // AlertsScreen
    String PROPERTY_UPDATE_ALERTS_LIST = "Update alerts JList"; // It repaints the JList


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
    String MQTT_BROKER = "tcp://192.168.1.103";
    int QoS0 = 0;
    int QoS1 = 1;
    int QoS2 = 2;

    String TOP_TOPIC_LEVEL = "greenhouse/";


    // Date constants
    SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("EEEE, MMM d HH:mm", new Locale("eu", "ES"));
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE, MMM d", new Locale("eu", "ES"));

    default String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }


    // Deprecated
    @Deprecated String PROPERTY_EXIT = "exit";
    @Deprecated String PROPERTY_LOAD_MENU = "Load Menu";
    @Deprecated String PROPERTY_GO_BACK = "Return to previous screen";
    @Deprecated String PROPERTY_UPDATE_GREENHOUSE_MODEL = "Update greenhouse model";
    @Deprecated String TOPIC_MODE = TOP_TOPIC_LEVEL + "mode";
    @Deprecated String TOPIC_FAN_1 = TOP_TOPIC_LEVEL + "fans/1";
    @Deprecated String TOPIC_FAN_2 = TOP_TOPIC_LEVEL + "fans/2";
    @Deprecated String TOPIC_SENSORS_CO2 = TOP_TOPIC_LEVEL + "sensors/ppm";

}
