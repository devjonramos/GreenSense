package com.greensense.view.renderer;

import com.greensense.constants.Images;
import com.greensense.model.AlertModel;
import com.greensense.view.components.AlertCard;


import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.ListCellRenderer;

import java.awt.Component;
import java.awt.BorderLayout;

public class AlertRenderer implements Images, ListCellRenderer<AlertModel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends AlertModel> list, AlertModel alertModel, int index, boolean isSelected, boolean cellHasFocus) {

        JPanel panel = new JPanel(new BorderLayout(0,0));
        panel.setOpaque(false);
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        (index == list.getModel().getSize() - 1) ? 0 : 24,
                        0
                )
        );

        AlertCard alertCard = new AlertCard(alertModel.getAlertType(), alertModel.getDescription(), alertModel.getSource());

        panel.add(alertCard, BorderLayout.CENTER);

        return panel;

    }

}
