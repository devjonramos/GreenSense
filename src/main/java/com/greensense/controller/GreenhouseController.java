package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import com.greensense.view.screens.Screen;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.greensense.constants.Constants;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.MQTTService;
import com.greensense.view.screens.GreenhouseScreen;
import com.greensense.view.screens.ScreenManager;

public class GreenhouseController implements Constants, ActionListener, MqttCallback {

    private ScreenManager screenManager = ScreenManager.getInstance();

    private GreenhouseScreen greenhouseScreen;
    private GreenhouseModel greenhouseModel;
    private MQTTService mqttService;

    public GreenhouseController(GreenhouseScreen greenhouseScreen, GreenhouseModel greenhouseModel) {
        this.greenhouseScreen = greenhouseScreen;
        this.greenhouseModel = greenhouseModel;

        this.greenhouseModel.addPropertyChangeListener(greenhouseScreen);

    }

    public void setGreenhouseModel(GreenhouseModel greenhouseModel) {
        this.greenhouseModel = greenhouseModel;
    }

    public void loadData(){

        greenhouseScreen.updateCO2(Integer.toString(greenhouseModel.getCO2level()));

    }

    public void startMQTTService(){

        mqttService = new MQTTService(MQTT_BROKER, greenhouseModel.getId(), this);
        mqttService.addTopic(MQTT_TOPIC_SENSOR_CO2);
        mqttService.addTopic(MQTT_TOPIC_SENSOR_ALERTS);
        mqttService.startService();

    }

    public void stopMQTTService() {
        
        mqttService.stopService();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        System.out.println(actionCommand);

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_GO_BACK, () -> {
            
            screenManager.showScreen("greenhouses");

        });

        commandHandler.put(PROPERTY_NEXT_GREENHOUSE, () -> {

            Screen screen = screenManager.getScreen("greenhouse");

            if (screen instanceof GreenhouseScreen){

                GreenhouseScreen greenhouseScreen = (GreenhouseScreen) screen;

                greenhouseScreen.setGreenhouseModel(null);

                screenManager.showScreen("greenhouse");

            }

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }


    // MQTT callback implementations
    // We use the "synchronized" keyword to avoid any concurrency problems. This way we ensure only one thread executes them at the same time
    @Override
    public synchronized void connectionLost(Throwable cause) {
        System.err.println("Connection to MQTT broker lost!" + cause);
    }

    @Override
    public synchronized void messageArrived(String topic, MqttMessage message) throws Exception {

        String content = new String(message.getPayload());

        switch (topic) {
            
            case MQTT_TOPIC_SENSOR_CO2:
                // greenhouse.updateCO2(content);
                greenhouseModel.setCO2level(Integer.parseInt(content));
            break;

            default: break;

        }

    }

    @Override
    public synchronized void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deliveryComplete'");
    }

}
