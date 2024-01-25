package com.greensense.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.constants.Constants;
import com.greensense.util.json.JSONManager;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greenhouses implements Constants {

    @Setter  private static int current = 0;

    private static Greenhouses instance;

    private List<GreenhouseModel> greenhouses;

    private Greenhouses(){

        greenhouses = new ArrayList<>();
        try {
            greenhouses = JSONManager.loadJSON(JSONManager.GREENHOUSES_FILE, new TypeReference<List<GreenhouseModel>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Greenhouses getInstance(){

        if (instance == null) {
            instance = new Greenhouses();
        }

        return instance;

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
        int index = greenhouses.indexOf(model);
        return index;
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
        return new ArrayList<>(greenhouses);
    }

}
