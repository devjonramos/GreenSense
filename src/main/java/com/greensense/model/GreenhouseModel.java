package com.greensense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greensense.constants.Constants;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GreenhouseModel implements Constants {

    public static int count = 1;

    private final String TOP_TOPIC_LEVEL = "greenhouses/";
    public final String TOPIC_SENSORS_CO2 = TOP_TOPIC_LEVEL + count + "/sensors/ppm";
    public final String TOPIC_MODE = TOP_TOPIC_LEVEL + count + "/mode";
    public final String TOPIC_FAN_1 = TOP_TOPIC_LEVEL + count + "/fans/1";
    public final String TOPIC_FAN_2 = TOP_TOPIC_LEVEL + count + "/fans/2";

    public enum Mode {
        MAN, AUTO;

        public Mode fromString(String mode) {

            for (Mode m : Mode.values()) {
                if(mode.equals(m.toString())) return m;
            }

            return null;

        }

    };

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("ppm")
    @Setter(AccessLevel.NONE)
    private int ppm;

    @JsonProperty("mode")
    private Mode mode;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PropertyChangeSupport support;

    public GreenhouseModel(String name, Mode mode, int ppm) {
        this.id = count++;
        this.name = name;
        this.mode = mode;
        this.ppm = ppm;

        this.support = new PropertyChangeSupport(this);

    }

    public GreenhouseModel(){
        this.support = new PropertyChangeSupport(this);
    }

    public GreenhouseModel(GreenhouseModel model){
        this.id = model.id;
        this.name = model.name;
        this.mode = model.mode;
        this.ppm = model.ppm;
        this.support = model.support;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
        support.firePropertyChange(PROPERTY_UPDATE_CO2, null, this.ppm);
    }

    public void update(GreenhouseModel model){

        this.setId(model.id);
        this.setName(model.name);
        this.setMode(model.mode);
        this.setPpm(model.ppm);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
