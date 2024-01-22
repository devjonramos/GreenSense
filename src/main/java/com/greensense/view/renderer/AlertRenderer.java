package com.greensense.view.renderer;

import com.greensense.Palette;
import com.greensense.constants.Images;
import com.greensense.view.components.AlertCard;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AlertRenderer implements Images, ListCellRenderer<String> {
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        //panel.setOpaque(false);
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        (index == list.getModel().getSize() - 1) ? 0 : 24,
                        0
                )
        );

        panel.add(new AlertCard());

        return panel;
    }
}
