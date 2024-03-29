package com.greensense.view.screens;

import com.greensense.Palette;
import com.greensense.controller.AlertsController;
import com.greensense.model.alert.AlertModel;
import com.greensense.model.alert.AlertsListModel;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.util.JListBuilder;
import com.greensense.view.components.Header;
import com.greensense.view.renderer.AlertRenderer;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class AlertsScreen extends JPanel implements Screen {

    private AlertsController controller;

    private JList<AlertModel> alertsList;
    private AlertsListModel alertsListModel;

    public AlertsScreen(){

        alertsListModel = new AlertsListModel();
        controller = new AlertsController(this, alertsListModel);


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
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        JLabel label = ComponentFactory.createLabel("Alertak", Palette.TEXT_PRIMARY_FG, InterMedium_32);

        alertsList = JListBuilder.<AlertModel>create()
                .setBackground(Color.WHITE)
                .setEnabled(true)
                .addListSelectionListener(controller)
                .withModel(alertsListModel)
                .withRenderer(new AlertRenderer())
                .build();

        JScrollPane alertsListScrollPane = new JScrollPane(alertsList){{
            setBorder(BorderFactory.createEmptyBorder());
            setOpaque(false);
            setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        }};

        GroupLayout layout = new GroupLayout(panel);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(label)
                .addComponent(alertsListScrollPane)

        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGap(32)
                .addComponent(alertsListScrollPane)

        );

        panel.setLayout(layout);

        return panel;

    }

    @Override
    public void load() {
        controller.loadAlerts();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property){

            case PROPERTY_UPDATE_ALERTS_LIST -> {
                if (alertsList != null) alertsList.repaint();
            }

        }

    }
}
