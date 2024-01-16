package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.GroupLayout.Alignment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;
import com.greensense.controller.GreenhouseCardsController;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.JSONManager;
import com.greensense.util.ComponentFactory.ButtonSize;
import com.greensense.view.components.GreenhouseCard;

public class GreenhouseCardsScreen extends JPanel implements Screen {

    private JSONManager jsonManager;

    private GreenhouseCardsController controller;

    private AbstractAction actionSearch, actionBack;

    private GreenhouseScreen greenhouseScreen;
    private List<GreenhouseModel> greenhouses;
    private List<GreenhouseCard> itemPanels;
    private JScrollPane greenhousesPanel;
    // private JList<Greenhouse> greenhouseList;
    // private DefaultListModel<Greenhouse> greenhouseListModel;

    JButton btnSearch;

    public GreenhouseCardsScreen() {

        jsonManager = new JSONManager();

        controller = new GreenhouseCardsController(this);
        this.createActions();

        itemPanels = new ArrayList<>();

        try {
            
            greenhouses = jsonManager.loadJSON("json/greenhouses.json", new TypeReference<List<GreenhouseModel>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }

        // GreenhouseModel m = new GreenhouseModel("Greenhouse #8", GreenhouseModel.Mode.AUTO, 234);

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeaderPanel();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        System.out.println(btnSearch.getSize());

        // foo();

    }

    public void foo(){

        itemPanels.forEach(itemPanel -> System.out.println(itemPanel.getSize()) );

        System.out.println(greenhousesPanel.getSize());
        System.out.println(greenhousesPanel.getWidth());

    }

    private void createActions() {
        actionBack = ActionBuilder.createAction("", PROPERTY_GO_BACK, controller).build();
        actionSearch = ActionBuilder.createAction("Bilatu", PROPERTY_SEARCH_GREENHOUSES, controller).build();
    }

    public JToolBar createHeaderPanel(){

		JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
		toolBar.setBorder(BorderCreator.createEmptyBorder(64, 24));

        JLabel logo = ComponentFactory.createLabel("GreenSense", Palette.LOGO_BG, PoppinsSemiBold_24);

        JButton btnBack = ComponentFactory.createIconButton(actionBack, ICON_SM_BACK);
        JButton btnPlus = ComponentFactory.createIconButton(null, ICON_SM_PLUS);
        JButton btnEdit = ComponentFactory.createIconButton(null, ICON_SM_EDIT);
        JButton btnSettings = ComponentFactory.createIconButton(null, ICON_SM_SETTINGS);
        JButton btnLogout = ComponentFactory.createIconButton(null, ICON_SM_LOGOUT);

        toolBar.add(btnBack);
        toolBar.add(Box.createRigidArea(new Dimension(16, 0)));
		toolBar.add(logo);
		toolBar.add(Box.createHorizontalGlue());
        toolBar.add(btnPlus);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnEdit);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.addSeparator();
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnSettings);
        toolBar.add(Box.createRigidArea(new Dimension(24, 0)));
        toolBar.add(btnLogout);

		return toolBar;

    }

    public JPanel createContentPanel(){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        GroupLayout layout = new GroupLayout(panel);

        JLabel title = ComponentFactory.createLabel("Greenhouses", Palette.TEXT_PRIMARY_FG, InterMedium_32);
    
        JTextField input = ComponentFactory.createTextField(JTextField.class);
        btnSearch = ComponentFactory.createPrimaryButton(actionSearch, ButtonSize.LARGE);

        greenhousesPanel = new JScrollPane(){

            {

                JPanel panel = new JPanel(new GridBagLayout()){{setOpaque(false);}};
                // panel.setBackground(Palette.MAIN_BG);
                // panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0; // Expand horizontally
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.WEST; 

                int i = 0;
                for (GreenhouseModel greenhouse : greenhouses) {

                    GreenhouseCard card = new GreenhouseCard(greenhouse, controller);

                    gbc.gridx = i % 3; // Columns within each row
                    gbc.insets = (i % 3 == 2) ? new Insets(0, 0, 32, 16) : new Insets(0, 0, 32, 32);

                    panel.add(card, gbc);

                    if (i % 3 == 2) {
                        
                        gbc.gridy++; // Move to the next row after three components
                        gbc.gridx = 0; // Reset column position

                    }

                    i++;

                }

                // setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                setViewportView(panel);
                setBorder(null);
                setOpaque(false);

            }

        };

        // greenhousesPanel = new JScrollPane(){

        //     {

        //         JPanel panel = new JPanel(new GridLayout(3, 3, 32, 32));
        //         panel.setBackground(Palette.MAIN_BG);
        //         // panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

        //         greenhouses.forEach(greenhouse -> {

        //             GreenhouseCard card = new GreenhouseCard(greenhouse, controller);

        //             panel.add(card);
        //             itemPanels.add(card);
                    
        //         });

        //         // setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //         setViewportView(panel);
        //         setBorder(null);
        //         setOpaque(false);

        //     }

        // };

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(title)
            .addGroup(layout.createSequentialGroup()
                .addComponent(input)
                .addGap(8)
                .addComponent(btnSearch)
            )
            .addComponent(greenhousesPanel)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(title)
            .addGap(16)
            .addGroup(layout.createParallelGroup(Alignment.CENTER)
                .addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
            )
            .addGap(32)
            .addComponent(greenhousesPanel)
        );

        panel.setLayout(layout);

        return panel;

    }

    public GreenhouseScreen getGreenhouseScreen(){
        return greenhouseScreen;
    }

    public void setGreenhouseScreen(GreenhouseScreen greenhouseScreen){
        this.greenhouseScreen = greenhouseScreen;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    @Override
    public void load() {
        
    }

    @Override
    public void dispose() {
        
    }

}