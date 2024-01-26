package com.greensense.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.greensense.constants.Constants;
import com.greensense.model.GreenhouseModel;
import com.greensense.model.Greenhouses;
import com.greensense.view.components.Form;
import com.greensense.view.components.FormElement;
import com.greensense.view.screens.GreenhouseCardsScreen;
import com.greensense.view.screens.GreenhouseScreen;
import com.greensense.view.screens.Screen;
import com.greensense.view.screens.ScreenManager;

public class GreenhouseCardsController implements Constants, ActionListener {

    private final ScreenManager screenManager = ScreenManager.getInstance();
    private GreenhouseCardsScreen view;

    public GreenhouseCardsController(GreenhouseCardsScreen view) {
        this.view = view;
        Greenhouses.getInstance().addPropertyChangeListener(this.view);
    }

    public List<GreenhouseModel> getGreenhouses(){
        return Greenhouses.getInstance().getGreenhouses();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        Map<String, Runnable> commandHandler = new HashMap<>();

        commandHandler.put(PROPERTY_SEARCH_GREENHOUSES, () -> {

            if (e.getSource() instanceof JButton) {
                
                JButton button = (JButton)e.getSource();

                Thread thread = new Thread(() -> {
                    System.out.println(view.getCardsPanel().getSize());
                    System.out.println(view.getCardsScrollPane().getSize());
                });

                thread.start();

            }

        });

        commandHandler.put(PROPERTY_OPEN_GREENHOUSE, () -> {

            if (e.getSource() instanceof JButton) {

                JButton button = (JButton)e.getSource();

                int greenhouseID = (int)button.getAction().getValue("greenhouseID");
                GreenhouseModel model = Greenhouses.getInstance().getGreenhouseByID(greenhouseID);
                Screen screen = screenManager.getScreen("greenhouse");

                if (screen instanceof GreenhouseScreen){

                    GreenhouseScreen greenhouseScreen = (GreenhouseScreen) screen;

                    greenhouseScreen.setModel(model);

                }
                else {

                    screen = new GreenhouseScreen(model);

                    screenManager.addScreen(screen, "greenhouse");

                }

                Greenhouses.setCurrent(Greenhouses.getInstance().indexOf(model));

                screenManager.showScreen("greenhouse");

                System.out.println(model.getId());

            }

        });

        commandHandler.put(PROPERTY_DELETE_GREENHOUSE, () -> {
            
            if (e.getSource() instanceof JButton) {

                JButton button = (JButton)e.getSource();

                GreenhouseModel greenhouseModel = (GreenhouseModel)button.getAction().getValue("greenhouse");

                String message = String.format("Ziur zaude '%s' negutegia ezabatu nahi duzula?", greenhouseModel.getName());

                int option = JOptionPane.showConfirmDialog(screenManager.getFrame(), message, "Ezabatu Negutegia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    Greenhouses.getInstance().remove(greenhouseModel);
                }

            }

        });

        commandHandler.put(PROPERTY_ADD_GREENHOUSE, () -> {

            if (e.getSource() instanceof JButton) {

                JButton button = (JButton)e.getSource();

                Form form = new Form(
                        screenManager.getFrame(), "Gehitu Negutegia", true, new GreenhouseFormHandler(),
                        new FormElement.Builder("name", "Negutegiren izena").build()
                );

            }

        });

        commandHandler.getOrDefault(actionCommand, () ->  System.out.println("default action") ).run();

    }

}
