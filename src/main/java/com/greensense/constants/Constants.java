package com.greensense.constants;

public interface Constants {

    String PROPERTY_EXIT = "exit";
    String PROPERTY_LOAD_MENU = "Load Menu";
    String PROPERTY_LOAD_HOMEPAGE = "Load Homepage";
    String PROPERTY_GO_BACK = "Return to previous screen";
    
    String PROPERTY_LOAD_PAGE_GREENHOUSES = "Load Greenhouse List";
    String PROPERTY_SEARCH_GREENHOUSES = "Search Greenhouses By Name";
    String PROPERTY_OPEN_GREENHOUSE = "Open Greenhouse";
    String PROPERTY_CHANGE_GREENHOUSE_NAME = "Change Greenhouse's Name";

    String PROPERTY_LOAD_PAGE_ALERTS = "Load Alerts";
    String PROPERTY_LOAD_PAGE_ANALYTICS = "Load Analytics";
    String PROPERTY_LOAD_PAGE_USERS = "Load Users";
    String PROPERTY_LOAD_PAGE_SETTINGS = "Load Settings";
    String PROPERTY_LOGIN = "Login to account";
    String PROPERTY_UPDATE_CO2 = "Update CO2 level";

    String PROPERTY_UPDATE_GREENHOUSE_MODEL = "Update greenhouse model";

    // Greenhouse Screen
    String PROPERTY_NEXT_GREENHOUSE = "Load next greenhouse screen";
    String PROPERTY_PREVIOUS_GREENHOUSE = "Load previous greenhouse screen";

    // MQTT Constants
    String MQTT_BROKER = "tcp://192.168.1.103";
    String MQTT_TOPIC_SENSOR_CO2 = "Sensor/CO2";
    String MQTT_TOPIC_SENSOR_ALERTS = "Sensor/Alerts";
    int MQTT_QoS = 2;

}
