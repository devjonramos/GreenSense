package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import com.greensense.model.Greenhouses;
import com.greensense.model.alert.AlertModel;
import com.greensense.model.alert.AlertType;
import com.greensense.model.alert.Alerts;
import com.greensense.view.components.togglebutton.ToggleButtonListener;
import com.greensense.view.components.togglebutton.ToggleEvent;
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

    private final ScreenManager screenManager = ScreenManager.getInstance();

    private GreenhouseScreen view;
    private GreenhouseModel greenhouseModel;
    private MQTTService mqttService;

    public GreenhouseController(GreenhouseScreen greenhouseScreen, GreenhouseModel greenhouseModel) {
        this.view = greenhouseScreen;
        this.greenhouseModel = greenhouseModel;

        setGreenhouseScreen(greenhouseScreen);
        setGreenhouseModel(greenhouseModel);
    }

    public void setGreenhouseScreen(GreenhouseScreen screen){
        this.view = screen;
    }

    public void setGreenhouseModel(GreenhouseModel greenhouseModel) {
        this.greenhouseModel = greenhouseModel;
        this.greenhouseModel.addPropertyChangeListener(view);
    }

    public void loadData(){

        greenhouseModel.updateView();

    }

    public void startMQTTService(){

        mqttService = new MQTTService(MQTT_BROKER, greenhouseModel.getName(), this);
        mqttService.addTopic(greenhouseModel.getTopicPPM());
        mqttService.startService();

        Alerts.getInstance().addAlert(new AlertModel(AlertType.ERROR, "Connection to MQTT Broker failed!", greenhouseModel.getName()));

    }

    public void stopMQTTService() {
        
        mqttService.stopService();

        Alerts.getInstance().addAlert(new AlertModel(AlertType.INFO, "Disconnected from MQTT Broker", greenhouseModel.getName()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        System.out.println(actionCommand);

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_NEXT_GREENHOUSE, () -> {

            GreenhouseModel nextGreenhouseModel = Greenhouses.getInstance().next();

            if (nextGreenhouseModel != null) {

                greenhouseModel.updateModel(nextGreenhouseModel);

                screenManager.reloadCurrentScreen(); // This will update the mqtt client to the new greenhouse

            }
            else {
                JOptionPane.showMessageDialog(view, "Azkenengo negutegian zaude!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        commandHandler.put(PROPERTY_PREVIOUS_GREENHOUSE, () -> {

            GreenhouseModel prevGreenhouseModel = Greenhouses.getInstance().previous();

            if (prevGreenhouseModel != null) {

                greenhouseModel.updateModel(prevGreenhouseModel);

                screenManager.reloadCurrentScreen(); // Refer to line 84

            }
            else {
                JOptionPane.showMessageDialog(view, "Lehenengo negutegian zaude!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

    @Override
    public void onToggle(ToggleEvent e) {

        String topic;
        String toggleCommand = e.getToggleCommand();
        boolean isSelected = e.isSelected();

        switch (toggleCommand) {
            case PROPERTY_TOGGLE_MODE -> {

                topic = greenhouseModel.getTopicMode();

//                view.setFanControlCardEnabled(1, !isSelected);
//                view.setFanControlCardEnabled(2, !isSelected);
                greenhouseModel.setMode(isSelected);
                if (isSelected){
                    greenhouseModel.setFan1(false);
                    greenhouseModel.setFan2(false);
                }

                //view.getModeControlCard().setName();
            }
            case PROPERTY_TOGGLE_FAN1 -> topic = greenhouseModel.getTopicFan(1);
            case PROPERTY_TOGGLE_FAN2 -> topic = greenhouseModel.getTopicFan(2);
            default -> topic = "";
        }

        if (mqttService != null && mqttService.isConnected()) {

            String message = Boolean.toString(e.isSelected());
            //System.out.println(message);

            try {

                //mqttService.publish(topic, message);
                mqttService.publish(topic, message, QoS2, false);

            } catch (MqttException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    // MQTT callback implementations
    // We use the "synchronized" keyword to avoid any concurrency problems. This way we ensure only one thread executes them at the same time
    @Override
    public synchronized void connectionLost(Throwable cause) {

        Alerts.getInstance().addAlert(new AlertModel(AlertType.ERROR, "Connection to MQTT broker lost!", greenhouseModel.getName()));

        System.err.println("Connection to MQTT broker lost!" + cause);
        cause.printStackTrace();

    }

    @Override
    public synchronized void messageArrived(String topic, MqttMessage message) throws Exception {

        String content = new String(message.getPayload()).replaceAll("[^a-zA-Z0-9]", "");

        //System.out.println(topic);

        if (topic.equals(greenhouseModel.getTopicPPM())){
            //System.out.println("Message arrived: " + content);
            greenhouseModel.setPpm(Integer.parseInt(content));
        }

    }

    @Override
    public synchronized void deliveryComplete(IMqttDeliveryToken token) {

        Alerts.getInstance().addAlert(new AlertModel(AlertType.INFO, "Message delivered to client", greenhouseModel.getName()));

    }

}
