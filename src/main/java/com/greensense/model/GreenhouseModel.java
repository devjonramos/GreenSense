package com.greensense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("CO2Level")
    @Setter(AccessLevel.NONE)
    private int CO2level;

    @JsonProperty("mode")
    private Mode mode;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PropertyChangeSupport support;

    public GreenhouseModel(String name, Mode mode, int co2) {
        this.id = "Greenhouse#" + count++;
        this.name = name;
        this.mode = mode;
        this.CO2level = co2;

        this.support = new PropertyChangeSupport(this);

    }

    public GreenhouseModel(){
        this.support = new PropertyChangeSupport(this);
    }

    public void setCO2level(int newCO2level) {
        this.CO2level = newCO2level;
        support.firePropertyChange(PROPERTY_UPDATE_CO2, null, newCO2level);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
