package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import com.greensense.Palette;
import com.greensense.controller.HomepageController;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.view.components.Header;

public class HomepageScreen extends JPanel implements Screen {

    private HomepageController controller;

    private AbstractAction actionGreenhouses, actionAlerts, actionAnalytics, actionUsers, actionSettings;

    public HomepageScreen() {

        super(new BorderLayout(0, 0));

        this.controller = new HomepageController(this);
        this.createActions();

        JToolBar header = new Header(false);
        JPanel content = createContentPanel();
        
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        setBackground(Palette.WHITE);

    }

    private void createActions() {

        actionGreenhouses = ActionBuilder.createAction("Negutegiak", PROPERTY_LOAD_PAGE_GREENHOUSES, controller).largeIcon(ICON_XL_GRID).build();
        actionAlerts = ActionBuilder.createAction("Alertak", PROPERTY_LOAD_PAGE_ALERTS, controller).largeIcon(ICON_XL_LOG).build();
        actionAnalytics = ActionBuilder.createAction("Estatistikak", PROPERTY_LOAD_PAGE_ANALYTICS, controller).largeIcon(ICON_XL_STATISTICS).build();
        actionUsers = ActionBuilder.createAction("Erabiltzaileak", PROPERTY_LOAD_PAGE_USERS, controller).largeIcon(ICON_XL_USERS).build();
        actionSettings = ActionBuilder.createAction("Ezarpenak", PROPERTY_LOAD_PAGE_SETTINGS, controller).largeIcon(ICON_XL_SETTINGS).build();

    }

    private JToolBar createHeaderPanel(){

		JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
		toolBar.setBorder(BorderCreator.createEmptyBorder(64, 24));

        JLabel logo = new JLabel("GreenSense");
        logo.setForeground(Palette.LOGO_BG);
        logo.setFont(PoppinsSemiBold_24);

        JButton btnSettings = ComponentFactory.createIconButton(null, ICON_SM_SETTINGS);
        JButton btnLogout = ComponentFactory.createIconButton(ACTION_LOGOUT, ICON_SM_LOGOUT);

		toolBar.add(logo);

		toolBar.add(Box.createHorizontalGlue());

        toolBar.add(btnSettings);
        toolBar.add(btnLogout);

        return new Header(false);

    }

    private JPanel createContentPanel(){

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 64, 16, 64));

        // String username = Session.getInstance().getUser().getName();
        // JTextArea heading = ComponentFactory.createTextArea("Arratsalde on,\n" + username + "!", Palette.TEXT_PRIMARY_FG, PoppinsSemiBold_64);

        JTextArea heading = ComponentFactory.createTextArea("Arratsalde on,\nJon", Palette.TEXT_PRIMARY_FG, PoppinsSemiBold_64);
        JLabel subheading = ComponentFactory.createLabel("Zer egin nahi duzu gaur?", Palette.TEXT_SECONDARY_FG, InterMedium_32);
        JPanel sectionsPanel = new JPanel(new GridLayout(2, 3, 48, 48)){

            {

                JButton btnGreenhouses = ComponentFactory.createSectionButton(actionGreenhouses);
                JButton btnAlerts = ComponentFactory.createSectionButton(actionAlerts);
                JButton btnAnalytics = ComponentFactory.createSectionButton(actionAnalytics);
                JButton btnUsers = ComponentFactory.createSectionButton(actionUsers);
                JButton btnSettings = ComponentFactory.createSectionButton(actionSettings);

                setOpaque(false);
                add(btnGreenhouses);
                add(btnAlerts);
                add(btnAnalytics);
                add(btnUsers);
                add(btnSettings);

            }

        };

        GroupLayout layout = new GroupLayout(panel);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(heading)
            .addComponent(subheading)
            .addComponent(sectionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, 756)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(heading, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(subheading)
            .addGap(64)
            .addComponent(sectionsPanel, 362, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        panel.setLayout(layout);

        return panel;

    }

    private JPanel createSectionsPanel() {

        JPanel panel = new JPanel(new GridLayout(2, 3, 48, 48));
        panel.setOpaque(false);

        JButton btnGreenhouses = ComponentFactory.createSectionButton(actionGreenhouses);
        JButton btnAlerts = ComponentFactory.createSectionButton(actionAlerts);
        JButton btnAnalytics = ComponentFactory.createSectionButton(actionAnalytics);
        JButton btnUsers = ComponentFactory.createSectionButton(actionUsers);
        JButton btnSettings = ComponentFactory.createSectionButton(actionSettings);

        panel.add(btnGreenhouses);
        panel.add(btnAlerts);
        panel.add(btnUsers);
        panel.add(btnAnalytics);
        panel.add(btnSettings);

        return panel;

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
