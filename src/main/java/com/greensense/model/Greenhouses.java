package com.greensense.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.constants.Constants;
import com.greensense.util.JSONManager;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Greenhouses implements Constants {

    private static int current = 0;
    private List<GreenhouseModel> greenhouses;

    public Greenhouses(){

        greenhouses = new ArrayList<>();
        try {
            greenhouses = JSONManager.loadJSON(JSONManager.GREENHOUSES_FILE, new TypeReference<List<GreenhouseModel>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public GreenhouseModel next(){

        current++;

        return greenhouses.get(current);

    }

    public GreenhouseModel previous(){

        current--;

        return greenhouses.get(current);

    }

    public List<GreenhouseModel> getGreenhouses() {
        return new ArrayList<>(greenhouses);
    }

}
