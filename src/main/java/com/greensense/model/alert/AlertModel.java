package com.greensense.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AlertModel {

    public static int count = 1;

    @JsonProperty("id")
    private int id;

    @JsonProperty("alertType")
    private AlertType alertType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("source")
    private String source;

    public AlertModel(AlertType alertType, String description, String source){
        this.id = count++;
        this.id = Alerts.getInstance().getAlerts().size();
        this.alertType = alertType;
        this.source = source;
        this.description = description;
    }

    public AlertModel(){}

}
