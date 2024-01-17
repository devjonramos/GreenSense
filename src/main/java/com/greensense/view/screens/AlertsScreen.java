package com.greensense.view.screens;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class AlertsScreen extends JPanel implements Screen {

    public AlertsScreen(){

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeaderPanel();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    public JToolBar createHeaderPanel(){

        JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
        toolBar.setBorder(BorderCreator.createEmptyBorder(64, 24));

        JLabel logo = ComponentFactory.createLabel("GreenSense", Palette.LOGO_BG, PoppinsSemiBold_24);

        JButton btnBack = ComponentFactory.createIconButton(null, ICON_SM_BACK);
        JButton btnSettings = ComponentFactory.createIconButton(null, ICON_SM_SETTINGS);
        JButton btnLogout = ComponentFactory.createIconButton(null, ICON_SM_LOGOUT);

        toolBar.add(btnBack);
        toolBar.add(Box.createRigidArea(new Dimension(16, 0)));
        toolBar.add(logo);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnSettings);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnLogout);

        return toolBar;

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
