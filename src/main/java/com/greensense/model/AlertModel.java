package com.greensense.model;

import lombok.Getter;

@Getter
public class AlertModel {

    private final AlertType alertType;
    private final String description;
    private final String source;

    public AlertModel(AlertType alertType, String description, String source){
        this.alertType = alertType;
        this.source = source;
        this.description = description;
    }

}
