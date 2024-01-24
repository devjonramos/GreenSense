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

    public static int count = 0;

    private final String TOP_TOPIC_LEVEL = "greenhouses/";
    public final String TOPIC_SENSORS_PPM = TOP_TOPIC_LEVEL + count + "/sensors/ppm";
    public final String TOPIC_MODE = TOP_TOPIC_LEVEL + count + "/mode";
    public final String TOPIC_FAN_1 = TOP_TOPIC_LEVEL + count + "/fans/1";
    public final String TOPIC_FAN_2 = TOP_TOPIC_LEVEL + count + "/fans/2";

    @Deprecated public enum Mode {
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

    @JsonProperty("isAuto")
    private boolean isAuto;

    @JsonProperty("fan1")
    private boolean fan1;

    @JsonProperty("fan2")
    private boolean fan2;

    @JsonProperty("ppm")
    @Setter(AccessLevel.NONE)
    private int ppm;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PropertyChangeSupport support;

    public GreenhouseModel(String name, boolean isAuto, int ppm) {
        this.id = count++;
        this.name = name;
        this.isAuto = isAuto;
        this.ppm = ppm;

        this.support = new PropertyChangeSupport(this);

    }

    public GreenhouseModel(){
        this.support = new PropertyChangeSupport(this);
    }

    public GreenhouseModel(GreenhouseModel model){
        this.id = model.id;
        this.name = model.name;
        this.isAuto = model.isAuto;
        this.ppm = model.ppm;
        this.support = model.support;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_PPM, null, this.ppm);
    }

    public void update(GreenhouseModel model){

        this.setId(model.id);
        this.setName(model.name);
        this.setAuto(model.isAuto);
        this.setPpm(model.ppm);

    }

    public void updateView(){

        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_NAME, null, this.name);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_PPM, null, this.ppm);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_MODE, null, this.isAuto);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_FAN_1, null, this.fan1);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_FAN_2, null, this.fan2);

    }

    public String getMode(){
        return (isAuto) ? "AUTO" : "MAN";
    }

    public String getTopicMode(){
        return TOP_TOPIC_LEVEL + this.id + "/mode";
    }

    public String getTopicPPM(){
        return TOP_TOPIC_LEVEL + this.id + "/sensors/ppm";
    }

    public String getTopicFan(int fanId){
        return TOP_TOPIC_LEVEL + this.id + "/fans/" + fanId;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
