package com.greensense.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.greensense.constants.Constants;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTService implements Constants {

    private Thread mqttThread;

    private MqttClient client;
    private String broker; 
    private String clientID; 
    private List<String> topics;
    private MqttCallback callback;

    public MQTTService(String broker, String clientID, MqttCallback callback) {

        this.broker = broker;
        this.clientID = clientID;
        this.topics = new ArrayList<>();
        this.callback = callback;
        
    }

    public void addTopic(String topic) {

        if (!topics.contains(topic)) {
            topics.add(topic);
        }

    }

    public void startService() {
        
        mqttThread = new Thread(() -> {

            try {

                client = new MqttClient(broker, clientID);
                client.setCallback(callback);
                System.out.println("Client ID: " + clientID);
                System.out.println("[MQTT] Connecting to broker: " + broker);
                client.connect();
                System.out.println("[MQTT] Connected");

                for (String topic : topics) {
                    client.subscribe(topic, QoS2);
                    System.out.println("[MQTT] Subscribed to topic:  " + topic);
                }
                System.out.println("[MQTT] Ready");

            } catch (MqttException e) {
                e.printStackTrace();
            }

        });
        mqttThread.start();

    }

    public synchronized void publish(String topic, String content, int qos, boolean retained) throws MqttException {

//        MqttMessage message = new MqttMessage(content.getBytes());
//        message.setQos(MQTT_QoS);
//
//        this.client.publish(topic, message);

        this.client.publish(topic, content.getBytes(), qos, retained);

    }

    public void stopService() {

        try {

            if (client != null && client.isConnected()) {
                client.disconnect();
                System.out.println("[MQTT] Disconnected from broker");
            }

            if (mqttThread != null && mqttThread.isAlive()) {
                mqttThread.interrupt();
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }



    public void setCallback(MqttCallback callback){
        this.callback = callback;
    }

}
