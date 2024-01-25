package com.greensense.view.screens;

import java.awt.BorderLayout;
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
import com.greensense.model.Greenhouses;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;
import com.greensense.view.components.GreenhouseCardsPanel;
import com.greensense.view.components.Header;
import lombok.Getter;

public class GreenhouseCardsScreen extends JPanel implements Screen {

    private GreenhouseCardsController controller;

    private AbstractAction actionSearch, actionAdd, actionEdit;

    private Greenhouses greenhouses = Greenhouses.getInstance();

    @Getter private GreenhouseCardsPanel cardsPanel;
    @Getter private JScrollPane cardsScrollPane;

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
        actionAdd = ActionBuilder.createAction("", PROPERTY_ADD_GREENHOUSE, controller).build();
        actionSearch = ActionBuilder.createAction("Bilatu", PROPERTY_SEARCH_GREENHOUSES, controller).build();
    }

    public JToolBar createHeader(){

        JButton btnAdd = ComponentFactory.createIconButton(actionAdd, ICON_SM_PLUS);
        JButton btnEdit = ComponentFactory.createIconButton(null, ICON_SM_EDIT);

		return new Header(true, btnAdd, btnEdit);

    }

    public JPanel createContentPanel(){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        GroupLayout layout = new GroupLayout(panel);

        JLabel title = ComponentFactory.createLabel("Greenhouses", Palette.TEXT_PRIMARY_FG, InterMedium_32);
    
        JTextField input = ComponentFactory.createTextField(JTextField.class);
        JButton btnSearch = ComponentFactory.createPrimaryButton(actionSearch, ButtonSize.LARGE);

        cardsPanel = new GreenhouseCardsPanel(controller);
        cardsScrollPane = new JScrollPane(cardsPanel){{
            setBorder(null);
            setOpaque(false);
        }};

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(title)
            .addGroup(layout.createSequentialGroup()
                .addComponent(input)
                .addGap(8)
                .addComponent(btnSearch)
            )
            .addComponent(cardsScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(title)
            .addGap(16)
            .addGroup(layout.createParallelGroup(Alignment.CENTER)
                .addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 40)
            )
            .addGap(32)
            .addComponent(cardsScrollPane)
        );

        panel.setLayout(layout);

        return panel;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property){
            case PROPERTY_DELETE_GREENHOUSE -> cardsPanel.updateCards();
        }

    }

    @Override
    public void load() {
        
    }

    @Override
    public void dispose() {
        
    }

}
