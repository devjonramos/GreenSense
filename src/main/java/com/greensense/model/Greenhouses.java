package com.greensense.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.constants.Constants;
import com.greensense.util.JSONManager;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greenhouses implements Constants {

    private static int current = 0;

    private static Greenhouses instance;

    private List<GreenhouseModel> greenhouses;

    private Greenhouses(){

        greenhouses = new ArrayList<>();
        try {
            greenhouses = JSONManager.loadJSON(JSONManager.GREENHOUSES_FILE, new TypeReference<List<GreenhouseModel>>(){});

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

    public GreenhouseModel getGreenhouseByID(String id){

        List<GreenhouseModel> filteredResults = getGreenhouses().stream().filter(
                g -> g.getId().equals(id)
        ).collect(Collectors.toList());

        if (filteredResults.size() == 1) return new GreenhouseModel(filteredResults.get(0));

        return  null;

    }

    public List<GreenhouseModel> getGreenhouses() {
        return new ArrayList<>(greenhouses);
    }

}
