package com.greensense.model;

import com.greensense.constants.Constants;

import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class UsersTableModel extends DefaultTableModel implements Constants {

    private final PropertyChangeSupport support;
    //private final String[] columnNames = {"Izena", "Abizena", "Role", "Last Seen"};
    private final List<String> colunmNames = List.of("Erabiltzailea", "Izena", "Role", "Azken Saioa");

    public UsersTableModel(){
        support = new PropertyChangeSupport(this);

        colunmNames.forEach(this::addColumn);

        loadData();
    }

    public void loadData(){

        setRowCount(0);

        List<User> users = Users.getInstance().getUsers();

        users.forEach(user -> addRow(new String[]{user.getUsername(), user.getFullName(), user.getRole().getName(), user.getLastSeen()}));

        fireTableDataChanged();
        support.firePropertyChange(PROPERTY_UPDATE_USERS_TABLE, null, null);

    }

    public Object[] getRow(int row){

        Object rowData[] = new Object[getColumnCount()];
        for (int i = 0; i < getColumnCount(); i++) {
            rowData[i] = getValueAt(row, i);
        }

        return rowData;

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
