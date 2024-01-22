package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Images;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AlertCard extends JPanel implements Images {

    public AlertCard(){

        JLabel iconLabel = new JLabel(ICON_XL_CHECK);
        JLabel nameLabel = new JLabel("Success");
        JLabel descLabel = new JLabel("Connected to MQTT broker successfully");

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d", Locale.US);
        String formattedDate = dateFormat.format(new Date());
        JLabel dateLabel = new JLabel(formattedDate);


        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(iconLabel)
                .addGap(16)
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                        .addComponent(descLabel)
                )
                .addGap(16)
                .addComponent(dateLabel)
        );

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(iconLabel)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGap(8)
                        .addComponent(descLabel)
                )
                .addComponent(dateLabel)
        );

        setLayout(layout);
        setBackground(Palette.GREEN_50);
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Palette.GREEN_400, 1, true),
                        BorderFactory.createEmptyBorder(16, 32, 16, 32)
                )
        );

    }

}
