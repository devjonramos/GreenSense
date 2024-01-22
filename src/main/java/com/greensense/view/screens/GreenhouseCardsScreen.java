package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.GroupLayout.Alignment;

import com.greensense.Palette;
import com.greensense.controller.GreenhouseCardsController;
import com.greensense.model.GreenhouseModel;
import com.greensense.model.Greenhouses;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;
import com.greensense.view.components.GreenhouseCard;
import com.greensense.view.components.Header;

public class GreenhouseCardsScreen extends JPanel implements Screen {

    private GreenhouseCardsController controller;

    private AbstractAction actionSearch;

    private Greenhouses greenhouses = Greenhouses.getInstance();

    public GreenhouseCardsScreen() {

        controller = new GreenhouseCardsController(this);
        this.createActions();

        JToolBar header = createHeader();
        JPanel content = createContentPanel();

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    private void createActions() {
        actionSearch = ActionBuilder.createAction("Bilatu", PROPERTY_SEARCH_GREENHOUSES, controller).build();
    }

    public JToolBar createHeader(){

        JButton btnPlus = ComponentFactory.createIconButton(null, ICON_SM_PLUS);
        JButton btnEdit = ComponentFactory.createIconButton(null, ICON_SM_EDIT);

		return new Header(true, btnPlus, btnEdit);

    }

    public JPanel createContentPanel(){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        GroupLayout layout = new GroupLayout(panel);

        JLabel title = ComponentFactory.createLabel("Greenhouses", Palette.TEXT_PRIMARY_FG, InterMedium_32);
    
        JTextField input = ComponentFactory.createTextField(JTextField.class);
        JButton btnSearch = ComponentFactory.createPrimaryButton(actionSearch, ButtonSize.LARGE);

        JScrollPane greenhousesPanel = new JScrollPane(){

            {

                JPanel panel = new JPanel(new GridBagLayout()){{setOpaque(false);}};

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0; // Expand horizontally
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.WEST;

                int i = 0;
                for (GreenhouseModel greenhouse : greenhouses.getGreenhouses()) {

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void load() {
        
    }

    @Override
    public void dispose() {
        
    }

}
