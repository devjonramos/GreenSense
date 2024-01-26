package com.greensense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"TOP_TOPIC_LEVEL", "TOPIC_SENSORS_PPM", "TOPIC_MODE", "TOPIC_FAN_1", "TOPIC_FAN_2"})
public class GreenhouseModel implements Constants {

    public static int count = 0;

    private final String TOP_TOPIC_LEVEL = "greenhouses/";
    @Deprecated public final String TOPIC_SENSORS_PPM = TOP_TOPIC_LEVEL + count + "/sensors/ppm";
    @Deprecated public final String TOPIC_MODE = TOP_TOPIC_LEVEL + count + "/mode";
    @Deprecated public final String TOPIC_FAN_1 = TOP_TOPIC_LEVEL + count + "/fans/1";
    @Deprecated public final String TOPIC_FAN_2 = TOP_TOPIC_LEVEL + count + "/fans/2";

//    @Deprecated public enum Mode {
//        MAN, AUTO;
//
//        public Mode fromString(String mode) {
//
//            for (Mode m : Mode.values()) {
//                if(mode.equals(m.toString())) return m;
//            }
//
//            return null;
//
//        }
//
//    }

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mode")
    private boolean mode;

    @JsonProperty("fan1")
    private boolean fan1;

    @JsonProperty("fan2")
    private boolean fan2;

    // @JsonProperty("ppm")
    @Setter(AccessLevel.NONE)
    private int ppm;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private PropertyChangeSupport support;

    public GreenhouseModel(int id, String name, boolean mode, int ppm, boolean fan1, boolean fan2) {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.ppm = ppm;
        this.fan1 = fan1;
        this.fan2 = fan2;

        this.support = new PropertyChangeSupport(this);

    }

    public GreenhouseModel(int id, String name){
        this(id, name, false, 0, false, false);
    }

    public GreenhouseModel(){
        this.support = new PropertyChangeSupport(this);
    }

    public GreenhouseModel(GreenhouseModel model){
//        this.id = model.id;
//        this.name = model.name;
//        this.isAuto = model.isAuto;
//        this.ppm = model.ppm;
//        this.fan1 = model.fan1;
//        this.fan2 = model.fan2;
//        this.support = model.support;

        updateModel(model);

    }

    public void updateModel(GreenhouseModel model){
        this.support = model.support;
        this.setId(model.id);
        this.setName(model.name);
        this.setMode(model.mode);
        this.setPpm(model.ppm);
        this.setFan1(model.fan1);
        this.setFan2(model.fan2);
    }

    public void updateView(){

        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_NAME, null, this.name);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_PPM, null, this.ppm);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_MODE, null, this.mode);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_FAN_1, null, this.fan1);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_FAN_2, null, this.fan2);

    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_PPM, null, this.ppm);
    }

    public void setMode(boolean mode){
        this.mode = mode;
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_MODE, null, this.mode);
    }

    public void setFan1(boolean fan1){
        this.fan1 = fan1;
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_FAN_1, null, this.fan1);
    }

    public void setFan2(boolean fan2){
        this.fan2 = fan2;
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSE_FAN_2, null, this.fan2);
    }

    public String getModeName(boolean mode) { return (mode) ? "AUTO" : "MAN"; }

    public String getModeName(){
        return getModeName(this.mode);
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

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof GreenhouseModel)) return false;

        GreenhouseModel other = (GreenhouseModel)o;

        return other.name.equals(this.name);

    }

    @Override
    public int hashCode() {

        int result = 1;


        Object name = this.getName();
        Object support = this.support;

        result = result * 59 + this.getId();
        result = result * 59 + (this.isMode() ? 79 : 97);
        result = result * 59 + (this.isFan1() ? 79 : 97);
        result = result * 59 + (this.isFan2() ? 79 : 97);
        result = result * 59 + this.getPpm();
        result = result * 59 + (name == null ? 43 : name.hashCode());
        result = result * 59 + (support == null ? 43 : support.hashCode());

        return result;

    }

}
