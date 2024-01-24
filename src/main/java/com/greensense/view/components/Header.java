package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;

import javax.swing.*;
import java.awt.*;

public class Header extends JToolBar implements Fonts, Images, Constants {

    public Header(boolean hasBackButton, Component... components){
        setOpaque(false);
        setBorder(BorderCreator.createEmptyBorder(64, 24));

        JLabel logo = ComponentFactory.createLabel("GreenSense", Palette.LOGO_BG, PoppinsSemiBold_24);

        JButton btnSettings = ComponentFactory.createIconButton(null, ICON_SM_SETTINGS);
        JButton btnLogout = ComponentFactory.createIconButton(ACTION_LOGOUT, ICON_SM_LOGOUT);

        if (hasBackButton){

            JButton btnBack = ComponentFactory.createIconButton(ACTION_BACK, ICON_SM_BACK);

            add(btnBack);
            add(Box.createRigidArea(new Dimension(16, 0)));

        }

        add(logo);

        add(Box.createHorizontalGlue());

        for(Component c : components){
            add(c);
            add(Box.createRigidArea(new Dimension(24, 0)));
        }

        if(components.length > 0) addSeparator();
        add(Box.createRigidArea(new Dimension(24, 0)));
        add(btnSettings);
        add(Box.createRigidArea(new Dimension(24, 0)));
        add(btnLogout);

    }

}
