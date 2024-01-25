package com.greensense.view.components;

import com.greensense.controller.GreenhouseCardsController;
import com.greensense.model.GreenhouseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GreenhouseCardsPanel extends JPanel {

    private GreenhouseCardsController controller;

    private GridBagConstraints gbc;

    public GreenhouseCardsPanel(GreenhouseCardsController controller){
        this.controller = controller;

        this.gbc = new GridBagConstraints();
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.weightx = 1.0; // Expand horizontally
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.NORTHWEST;

        setOpaque(false);
        setLayout(new GridBagLayout());
        renderGreenhouses();

    }

    private void renderGreenhouses(){

        int i = 0;
        for (GreenhouseModel greenhouse : controller.getGreenhouses()) {

            GreenhouseCard card = new GreenhouseCard(greenhouse, controller);

            gbc.gridx = i % 3; // Columns within each row
            gbc.insets = (i % 3 == 2)
                    ? new Insets(0, 0, 32, 16)
                    : new Insets(0, 0, 32, 32);

            this.add(card, gbc);

            if (i % 3 == 2) {

                gbc.gridy++; // Move to the next row after three components
                gbc.gridx = 0; // Reset column position

            }

            i++;

        }

    }

    public void updateCards(){

        this.removeAll();
        this.renderGreenhouses();
        this.revalidate();
        this.repaint();

    }

}
