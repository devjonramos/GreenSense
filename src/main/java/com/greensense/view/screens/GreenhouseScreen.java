package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;
import com.greensense.controller.GreenhouseController;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;
import com.greensense.view.components.GreenhouseCard;
import com.greensense.view.components.infocard.ControlCard;
import com.greensense.view.components.infocard.DisplayCard;
import com.greensense.view.components.togglebutton.ToggleButton;
import com.greensense.view.components.togglebutton.ToggleButtonListener;

public class GreenhouseScreen extends JPanel implements Screen {

    private GreenhouseController controller;

    private GreenhouseModel greenhouseModel;

    private AbstractAction actionBack, actionNext, actionPrevious;

    private DisplayCard displayCard;

    public GreenhouseScreen(GreenhouseModel greenhouseModel) {

        this.setGreenhouseModel(greenhouseModel);

        controller = new GreenhouseController(this, this.greenhouseModel);
        this.createActions();

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeaderPanel();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    private void createActions() {
        actionBack = ActionBuilder.createAction("", PROPERTY_GO_BACK, controller).build();
        actionNext = ActionBuilder.createAction("", PROPERTY_NEXT_GREENHOUSE, controller).build();
        actionPrevious = ActionBuilder.createAction("", PROPERTY_PREVIOUS_GREENHOUSE, controller).build();
    }

    public JToolBar createHeaderPanel(){

		JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
		toolBar.setBorder(BorderCreator.createEmptyBorder(64, 24));

        JLabel logo = ComponentFactory.createLabel("GreenSense", Palette.LOGO_BG, PoppinsSemiBold_24);

        JButton btnBack = ComponentFactory.createIconButton(actionBack, ICON_SM_BACK);
        JButton btnNavPrev = ComponentFactory.createIconButton(actionPrevious, ICON_SM_PREVIOUS);
        JButton btnNavNext = ComponentFactory.createIconButton(actionNext, ICON_SM_NEXT);
        JButton btnAlerts = ComponentFactory.createIconButton(null, ICON_SM_EDIT);
        JButton btnSettings = ComponentFactory.createIconButton(null, ICON_SM_SETTINGS);
        JButton btnLogout = ComponentFactory.createIconButton(null, ICON_SM_LOGOUT);

		toolBar.add(btnBack);
        toolBar.add(Box.createRigidArea(new Dimension(16, 0)));
        toolBar.add(logo);
		toolBar.add(Box.createHorizontalGlue());
        toolBar.add(btnNavPrev);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnNavNext);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.addSeparator();
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnSettings);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnLogout);

		return toolBar;

    }

    public JPanel createContentPanel() {

        JPanel panel = new JPanel();

        JLabel nameLabel = ComponentFactory.createLabel(greenhouseModel.getName(), Palette.TEXT_PRIMARY_FG, InterMedium_48);

        ControlCard controlCard1 = new ControlCard("Modua", ICON_MD_TOOL);
        ControlCard controlCard2 = new ControlCard("Haizagailua1", ICON_MD_WIND);
        ControlCard controlCard3 = new ControlCard("Haizagailua2", ICON_MD_WIND);
        DisplayCard displayCard2 = new DisplayCard("Grafikoa", "542");
        displayCard = new DisplayCard("CO2", "542");

        GroupLayout layout = new GroupLayout(panel);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(nameLabel)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlCard1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addGap(24)
                .addComponent(controlCard2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addGap(24)
                .addComponent(controlCard3, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(displayCard2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(24)
                .addComponent(displayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(nameLabel)
            .addGap(48)
            .addGroup(layout.createParallelGroup()
                .addComponent(controlCard1)
                .addComponent(controlCard2)
                .addComponent(controlCard3)
            )
            .addGap(24)
            .addGroup(layout.createParallelGroup()
                .addComponent(displayCard2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
                .addComponent(displayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
            )
            
        );

        panel.setLayout(layout);
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        return panel;

    }

    // public JPanel createAttributesPanel() {

    //     JPanel panel = new JPanel(new GridLayout(2, 4));

    //     CO2Label = new JLabel("");
    //     AttributeCard controlCard1 = new AttributeCard("Modua", ICON_LG_ALERT);
    //     AttributeCard controlCard2 = new AttributeCard("Ureztatze", ICON_LG_ALERT);
    //     AttributeCard controlCard3 = new AttributeCard("CO2", ICON_LG_ALERT);
    //     AttributeCard controlCard4 = new AttributeCard("Haizea", ICON_LG_ALERT);
    //     AttributeCard controlCard5 = new AttributeCard("Graph", ICON_LG_ALERT);
    //     AttributeCard controlCard6 = new AttributeCard("CO2", ICON_LG_ALERT);

    //     // panel.add(CO2Label);
    //     panel.add(controlCard1);
    //     panel.add(controlCard2);
    //     panel.add(controlCard3);
    //     panel.add(controlCard4);
    //     panel.add(controlCard5);
    //     panel.add(controlCard6);

    //     return panel;

    // }

    public void setGreenhouseModel(GreenhouseModel greenhouseModel) { this.greenhouseModel = greenhouseModel; }

    public void updateCO2(String newCO2level) {

        displayCard.setValue(newCO2level);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property) {

			case PROPERTY_UPDATE_CO2:

                int CO2Level = (int)evt.getNewValue();

                updateCO2(Integer.toString(CO2Level));

			break;

			default: break;

		}

    }

    @Override
    public void load() {
        
        controller.setGreenhouseModel(greenhouseModel);
        controller.loadData();
        controller.startMQTTService();
    }

    @Override
    public void dispose() {
        controller.stopMQTTService();
    }

}
