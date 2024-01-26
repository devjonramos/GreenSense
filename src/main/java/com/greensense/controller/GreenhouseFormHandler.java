package com.greensense.controller;

import com.greensense.model.GreenhouseModel;
import com.greensense.model.Greenhouses;
import com.greensense.view.components.Form;

import javax.swing.JOptionPane;
import java.util.Map;

public class GreenhouseFormHandler implements FormHandler{
    @Override
    public boolean handleFormSubmission(Map<String, Object> formData, Form form) {

        GreenhouseModel greenhouse = new GreenhouseModel(
                Greenhouses.getInstance().getGreenhouses().size() + 1,
                (String)formData.get("name")
        );

        boolean greenhouseAdded = Greenhouses.getInstance().addGreenhouse(greenhouse);

        if (greenhouseAdded){
            JOptionPane.showMessageDialog(form, "Negutegia arazo gabe sortu da", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(form, "Izen hori hartuta dago!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return greenhouseAdded;
    }
}
