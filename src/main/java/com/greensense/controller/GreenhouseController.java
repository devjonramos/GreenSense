package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import com.greensense.model.Greenhouses;
import com.greensense.view.components.togglebutton.ToggleButtonListener;
import com.greensense.view.screens.Screen;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.greensense.constants.Constants;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.MQTTService;
import com.greensense.view.screens.GreenhouseScreen;
import com.greensense.view.screens.ScreenManager;

import javax.swing.*;

public class GreenhouseController implements Constants, ActionListener, ToggleButtonListener, MqttCallback {

    private ScreenManager screenManager = ScreenManager.getInstance();

    private GreenhouseScreen view;
    private GreenhouseModel greenhouseModel;
    private MQTTService mqttService;

    public GreenhouseController(GreenhouseScreen greenhouseScreen, GreenhouseModel greenhouseModel) {
        this.view = greenhouseScreen;
        this.greenhouseModel = greenhouseModel;

        this.greenhouseModel.addPropertyChangeListener(greenhouseScreen);
    }

    public void setGreenhouseModel(GreenhouseModel greenhouseModel) { this.greenhouseModel = greenhouseModel; }

    public void loadData(){

        view.updateCO2(Integer.toString(greenhouseModel.getCO2level()));

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

            GreenhouseModel nextGreenhouseModel = Greenhouses.getInstance().next();

            if (nextGreenhouseModel != null) {

                greenhouseModel.update(nextGreenhouseModel);

                screenManager.reloadCurrentScreen();

            }
            else {
                JOptionPane.showMessageDialog(view, "Azkenengo negutegian zaude!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        commandHandler.put(PROPERTY_PREVIOUS_GREENHOUSE, () -> {

            GreenhouseModel prevGreenhouseModel = Greenhouses.getInstance().previous();

            if (prevGreenhouseModel != null) {

                greenhouseModel.update(prevGreenhouseModel);

                screenManager.reloadCurrentScreen();

            }
            else {
                JOptionPane.showMessageDialog(view, "Lehenengo negutegian zaude!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

    @Override
    public void onSelected(String toggleButtonName, boolean selected) {

        System.out.println(selected);

        if (mqttService != null) {

            String topic = greenhouseModel.getName() + "/" + toggleButtonName;
            String message = (selected) ? "1" : "0";

            try {

                //mqttService.publish(topic, message);
                mqttService.publish("Topic", "message");

            } catch (MqttException e) {
                throw new RuntimeException(e);
            }

        }

    }

    // MQTT callback implementations
    // We use the "synchronized" keyword to avoid any concurrency problems. This way we ensure only one thread executes them at the same time
    @Override
    public synchronized void connectionLost(Throwable cause) {
        System.err.println("Connection to MQTT broker lost!" + cause);
        cause.printStackTrace();
    }

    @Override
    public synchronized void messageArrived(String topic, MqttMessage message) throws Exception {

        String content = new String(message.getPayload()).replaceAll("[^a-zA-Z0-9]", "");

        //System.out.println("Message arrived");

        switch (topic) {
            
            case MQTT_TOPIC_SENSOR_CO2:
                // System.out.println(content);
                // greenhouse.updateCO2(content);
                greenhouseModel.setCO2level(Integer.parseInt(content));
            break;

            default: break;

        }

    }

    @Override
    public synchronized void deliveryComplete(IMqttDeliveryToken token) {

    }

}
