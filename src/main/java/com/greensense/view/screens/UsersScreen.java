package com.greensense.view.screens;

import com.greensense.Palette;
import com.greensense.controller.UsersController;
import com.greensense.model.UsersTableModel;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;
import com.greensense.view.components.Header;
import com.greensense.view.components.UsersTable;
import lombok.Getter;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;

public class UsersScreen extends JPanel implements Screen{

    private UsersController controller;

    private AbstractAction actionSearch, actionAdd, actionDelete;

    private UsersTableModel usersTableModel;
    @Getter private JTable usersTable;

    public UsersScreen(){

        usersTableModel = new UsersTableModel();
        controller = new UsersController(this, usersTableModel);
        this.createActions();

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeader();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    private void createActions() {
        actionAdd = ActionBuilder.createAction("", PROPERTY_ADD_USER, controller).build();
        actionDelete = ActionBuilder.createAction("", PROPERTY_DELETE_USER, controller).build();
        actionSearch = ActionBuilder.createAction("Bilatu", PROPERTY_SEARCH_GREENHOUSES, controller).build();
    }

    public JToolBar createHeader(){

        JButton btnAddUser = ComponentFactory.createIconButton(actionAdd, ICON_SM_PLUS);
        JButton btnEditUser = ComponentFactory.createIconButton(actionDelete, ICON_SM_TRASH);

		return new Header(true, btnAddUser, btnEditUser);

    }

    public JPanel createContentPanel(){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        GroupLayout layout = new GroupLayout(panel);

        JLabel title = ComponentFactory.createLabel("Erabiltzaileak", Palette.TEXT_PRIMARY_FG, InterMedium_32);
    
        // Search input
        JTextField input = ComponentFactory.createTextField(JTextField.class);
        JButton btnSearch = ComponentFactory.createPrimaryButton(actionSearch, ButtonSize.LARGE);
        

        // Users table

        usersTable = new UsersTable(usersTableModel);

        JScrollPane tableScrollPane = new JScrollPane(usersTable){{
            setBorder(BorderFactory.createEmptyBorder());
            setOpaque(false);
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        }};

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(title)
            .addGroup(layout.createSequentialGroup()
                .addComponent(input)
                .addGap(8)
                .addComponent(btnSearch)
            )
            .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(title)
            .addGap(16)
            .addGroup(layout.createParallelGroup(Alignment.CENTER)
                .addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 40)
            )
            .addGap(32)
            .addComponent(tableScrollPane)
        );

        panel.setLayout(layout);

        return panel;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property){
            case PROPERTY_UPDATE_USERS_TABLE -> {
                if (usersTable != null) usersTable.repaint();
            }
        }

    }

    @Override
    public void load() {
        usersTableModel.loadData();
    }

    @Override
    public void dispose() {

    }

}
