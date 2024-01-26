package com.greensense.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greensense.constants.Constants;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AlertModel implements Constants {

    public static int count = 1;

    @JsonProperty("id")
    @Setter(AccessLevel.NONE)
    private long id;

    @JsonProperty("alertType")
    private AlertType alertType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("source")
    private String source;

    @JsonProperty("date")
    private String date;

    public AlertModel(AlertType alertType, String description, String source){
        this.id = System.currentTimeMillis();
        //this.id = Alerts.getInstance().getAlerts().size();
        this.alertType = alertType;
        this.source = source;
        this.description = description;
        this.date = capitalize(DATETIME_FORMAT.format(new Date()));
    }

    public AlertModel(){}

}
