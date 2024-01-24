package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;
import com.greensense.model.alert.AlertType;
import com.greensense.util.ComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AlertCard extends JPanel implements Images, Fonts, Constants {

    public AlertCard(AlertType alertType, String description, String source){

        JLabel iconLabel = new JLabel(alertType.getIcon());
        JLabel nameLabel = ComponentFactory.createLabel(alertType.getTitle(), Palette.TEXT_PRIMARY_FG, InterMedium_24);
        JLabel descLabel = ComponentFactory.createLabel(description, Palette.TEXT_PRIMARY_FG, InterRegular_16);
        JLabel sourceLabel = ComponentFactory.createLabel(source, Palette.ALERT_SOURCE_FG, InterRegular_16, RIGHT_ALIGNMENT);
        JLabel dateLabel = ComponentFactory.createLabel(capitalize(DATE_FORMAT.format(new Date())), Palette.TEXT_PRIMARY_FG, InterRegular_16, RIGHT_ALIGNMENT);

        JPanel centerPanel = ComponentFactory.createComponentGroup(nameLabel, descLabel, 8, BoxLayout.Y_AXIS);
        JPanel rightPanel = ComponentFactory.createComponentGroup(sourceLabel, dateLabel, 8, BoxLayout.Y_AXIS);


        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(alertType.getBackgroundColor());
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 10, 1, 1, alertType.getBorderColor()),
                        BorderFactory.createEmptyBorder(16, 32, 16, 32)
                )
        );

        add(iconLabel);
        add(Box.createRigidArea(new Dimension(16, 0)));
        add(centerPanel);
        add(Box.createHorizontalGlue());
        add(rightPanel);

    }

    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}
