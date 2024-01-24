package com.greensense.model.alert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.util.json.JSONManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Alerts {

    private static Alerts instance;

    private List<AlertModel> alerts;

    private Alerts(){

        alerts = new ArrayList<>();
        try {
            alerts = JSONManager.loadJSON(JSONManager.ALERTS_FILE, new TypeReference<>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Alerts getInstance(){

        if (instance == null){
            instance = new Alerts();
        }

        return instance;

    }

    public void addAlert(AlertModel alert) {

        if (!alerts.contains(alert)) {
            alerts.add(alert);
        }

    }

    public void save(){

        try {

            JSONManager.writeToJSON(JSONManager.ALERTS_FILE, alerts);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<AlertModel> getAlerts() {
        return new ArrayList<>(alerts);
    }

}
