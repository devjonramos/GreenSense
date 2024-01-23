package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;
import com.greensense.model.AlertType;
import com.greensense.util.ComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AlertCard extends JPanel implements Images, Fonts {

    public AlertCard(AlertType alertType, String description, String source){

        JLabel iconLabel = new JLabel(alertType.getIcon());
        JLabel nameLabel = ComponentFactory.createLabel(alertType.getTitle(), Palette.TEXT_PRIMARY_FG, InterMedium_24);
        JLabel descLabel = ComponentFactory.createLabel(description, Palette.TEXT_PRIMARY_FG, InterRegular_16);

        JPanel centerPanel = new JPanel(){{

            BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

            setLayout(boxLayout);
            setOpaque(false);

            add(nameLabel);
            add(descLabel);

        }};

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d", Locale.US);
        JLabel dateLabel = ComponentFactory.createLabel(dateFormat.format(new Date()), Palette.TEXT_PRIMARY_FG, InterRegular_16);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(alertType.getBackgroundColor());
        setBorder(
                BorderFactory.createCompoundBorder(
                        //BorderFactory.createLineBorder(alertType.getBorderColor(), 1, true),
                        BorderFactory.createMatteBorder(1, 10, 1, 1, alertType.getBorderColor()),
                        BorderFactory.createEmptyBorder(16, 32, 16, 32)
                )
        );

        add(iconLabel);
        add(Box.createRigidArea(new Dimension(16, 0)));
        add(centerPanel);
        add(Box.createHorizontalGlue());
        add(dateLabel);

    }

}
