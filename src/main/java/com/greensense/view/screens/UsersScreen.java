package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.util.List;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.greensense.Palette;
import com.greensense.controller.UsersController;
import com.greensense.model.User;
import com.greensense.model.Users;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;
import com.greensense.view.components.Header;

public class UsersScreen extends JPanel implements Screen{

    private UsersController controller;

    private AbstractAction actionSearch;
    
    public UsersScreen(){

        controller = new UsersController(this);
        this.createActions();

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeader();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    private void createActions() {
        actionSearch = ActionBuilder.createAction("Bilatu", PROPERTY_SEARCH_GREENHOUSES, controller).build();
    }

    public JToolBar createHeader(){

        JButton btnAddUser = ComponentFactory.createIconButton(null, ICON_SM_PLUS);
        JButton btnEditUser = ComponentFactory.createIconButton(null, ICON_SM_EDIT);

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
        List<User> users = Users.getInstance().getUsers();
        String[][] usersTableData = new String[users.size()][];

        int i = 0;
        for (User user : users) {
            
            String[] userData = {user.getName(), user.getSurname(), user.getRoleAsString(), user.getLastSeen()};

            usersTableData[i] = userData;

            i++;

        }


        JTable usersTable = createUsersTable(usersTableData);
        usersTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
                // Set padding for the cell
                setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
        
                return cellRendererComponent;
            }

        });


        JTableHeader usersTableHeader = usersTable.getTableHeader();
        usersTableHeader.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 0, 1, Palette.GREEN_400),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
            )
        );
        usersTableHeader.setDefaultRenderer((jtable, value, isSelected, hasFocus, row, column) -> {

            JPanel cellPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
                setBackground(Palette.GREEN_100);
                setBorder(
                    BorderFactory.createEmptyBorder(16, 16, 16, 16)
                );
            }};

            JLabel cellLabel = ComponentFactory.createLabel(value.toString(), Palette.TEXT_PRIMARY_FG, InterSemiBold_16);

            cellPanel.add(cellLabel);

            return cellPanel;

        });

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

    private static JTable createUsersTable(String[][] usersTableData) {

        String[] usersTableColumnNames = {"Izena", "Abizena", "Role", "Last Seen"};

        DefaultTableModel usersTableModel = new DefaultTableModel(usersTableData, usersTableColumnNames);

        JTable usersTable = new JTable(usersTableModel);
        usersTable.setFont(InterSemiBold_16);
        usersTable.setForeground(Palette.TEXT_PRIMARY_FG);
        usersTable.setBorder(
            BorderFactory.createMatteBorder(0, 1, 1, 1, Palette.GREEN_400)
        );
        usersTable.setRowHeight(51);
        usersTable.setGridColor(Palette.GREEN_100);
        usersTable.setShowHorizontalLines(true);
        usersTable.setShowVerticalLines(false);

        return usersTable;

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
