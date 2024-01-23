package com.greensense.view.screens;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.controller.AlertsController;
import com.greensense.model.AlertModel;
import com.greensense.model.AlertType;
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
    private DefaultListModel<AlertModel> alertsListModel;

    public AlertsScreen(){

        controller = new AlertsController(this);

        alertsListModel = new DefaultListModel<>();
        alertsListModel.addElement(new AlertModel(AlertType.SUCCESS, "Connected to MQTT Broker successfully 1", "Greenhouse1"));
        alertsListModel.addElement(new AlertModel(AlertType.SUCCESS, "Connected to MQTT Broker successfully 2", "Greenhouse2"));
        alertsListModel.addElement(new AlertModel(AlertType.SUCCESS, "Connected to MQTT Broker successfully 3", "Greenhouse3"));
        alertsListModel.addElement(new AlertModel(AlertType.SUCCESS, "Connected to MQTT Broker successfully 4", "Greenhouse4"));
        alertsListModel.addElement(new AlertModel(AlertType.SUCCESS, "Connected to MQTT Broker successfully 5", "Greenhouse5"));
        alertsListModel.addElement(new AlertModel(AlertType.SUCCESS, "Connected to MQTT Broker successfully 6", "Greenhouse6"));

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

    }

    @Override
    public void dispose() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
