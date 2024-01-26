package com.greensense.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.constants.Constants;
import com.greensense.util.json.JSONManager;
import lombok.Setter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greenhouses implements Constants {

    @Setter  private static int current = 0;

    private static Greenhouses instance;

    private List<GreenhouseModel> greenhouses;

    private PropertyChangeSupport support;

    private Greenhouses(){

        greenhouses = new ArrayList<>();
        try {
            greenhouses = JSONManager.loadJSON(JSONManager.GREENHOUSES_FILE, new TypeReference<List<GreenhouseModel>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }

        support = new PropertyChangeSupport(this);

    }

    public static Greenhouses getInstance(){

        if (instance == null) {
            instance = new Greenhouses();
        }

        return instance;

    }

    public boolean addGreenhouse(GreenhouseModel greenhouse){

        if (!greenhouseExists(greenhouse)){

            greenhouses.add(greenhouse);

            support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSES, null, null);

            return true;
        }

        return false;

    }

    public void remove(GreenhouseModel model) {
        greenhouses.remove(model);
        support.firePropertyChange(PROPERTY_UPDATE_GREENHOUSES, null, null);
    }

    public boolean greenhouseExists(GreenhouseModel other) {

        for (GreenhouseModel greenhouse : greenhouses) {
            if(greenhouse.equals(other)) return true;
        }

        return false;

    }

    public GreenhouseModel next(){

        if (current == greenhouses.size() - 1) return null;

        current++;

        return greenhouses.get(current);

    }

    public GreenhouseModel previous(){

        if (current == 0) return null;

        current--;

        return greenhouses.get(current);

    }

    public int indexOf(GreenhouseModel model){
        return greenhouses.indexOf(model);
    }



    public GreenhouseModel getGreenhouseByID(int id){

        List<GreenhouseModel> filteredResults = getGreenhouses().stream().filter(
                g -> g.getId() == id
        ).collect(Collectors.toList());

        if (filteredResults.size() == 1) return new GreenhouseModel(filteredResults.get(0));

        return  null;

    }



    public void save(){

        try {

            JSONManager.writeToJSON(JSONManager.GREENHOUSES_FILE, greenhouses);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<GreenhouseModel> getGreenhouses() {
        return greenhouses;// new ArrayList<>(greenhouses);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }


}
