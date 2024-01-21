package com.greensense.view.screens;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.view.components.Header;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class AlertsScreen extends JPanel implements Screen {

    public AlertsScreen(){

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeader();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    public JToolBar createHeader(){

        return new Header(true);

    }

    private JPanel createContentPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = ComponentFactory.createLabel("Alertak", Palette.TEXT_PRIMARY_FG, InterMedium_32);

        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));
        panel.add(label);

        return panel;

    }



    @Override
    public void load() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
