package com.greensense.view.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.greensense.Palette;
import com.greensense.controller.GreenhouseController;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.view.components.Header;
import com.greensense.view.components.infocard.ControlCard;
import com.greensense.view.components.infocard.DisplayCard;
import lombok.Getter;

public class GreenhouseScreen extends JPanel implements Screen {

    private GreenhouseController controller;

    private GreenhouseModel greenhouseModel;

    private AbstractAction actionNext, actionPrevious;

    @Getter private ControlCard modeControlCard, windControlCard1, windControlCard2;
    @Getter private DisplayCard ppmDisplayCard, graphicDisplayCard;
    // @Getter private DisplayCard graphicDisplayCard;

    public GreenhouseScreen(GreenhouseModel greenhouseModel) {

        this.setGreenhouseModel(greenhouseModel);

        controller = new GreenhouseController(this, this.greenhouseModel);
        this.createActions();

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeader();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    private void createActions() {
        //actionBack = ActionBuilder.createAction("", PROPERTY_GO_BACK, controller).build();
        actionNext = ActionBuilder.createAction("", PROPERTY_NEXT_GREENHOUSE, controller).build();
        actionPrevious = ActionBuilder.createAction("", PROPERTY_PREVIOUS_GREENHOUSE, controller).build();
    }

    public JToolBar createHeader(){

        JButton btnNavPrev = ComponentFactory.createIconButton(actionPrevious, ICON_SM_PREVIOUS);
        JButton btnNavNext = ComponentFactory.createIconButton(actionNext, ICON_SM_NEXT);

		return new Header(true, btnNavPrev, btnNavNext);

    }

    public JPanel createContentPanel() {

        JPanel panel = new JPanel();

        JLabel nameLabel = ComponentFactory.createLabel(greenhouseModel.getName(), Palette.TEXT_PRIMARY_FG, InterMedium_48);

        modeControlCard = new ControlCard("Modua", ICON_MD_TOOL, PROPERTY_TOGGLE_MODE, controller);
        windControlCard1 = new ControlCard("Haizagailua1", ICON_MD_WIND, PROPERTY_TOGGLE_FAN1, controller);
        windControlCard2 = new ControlCard("Haizagailua2", ICON_MD_WIND, PROPERTY_TOGGLE_FAN2, controller);
        graphicDisplayCard = new DisplayCard("Grafikoa", "967");
        ppmDisplayCard = new DisplayCard("CO2", "542");

        GroupLayout layout = new GroupLayout(panel);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(nameLabel)
            .addGroup(layout.createSequentialGroup()
                .addComponent(modeControlCard, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addGap(24)
                .addComponent(windControlCard1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addGap(24)
                .addComponent(windControlCard2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
            )
            .addGroup(layout.createSequentialGroup()
                .addComponent(graphicDisplayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 438)
                .addGap(24)
                .addComponent(ppmDisplayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 438)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(nameLabel)
            .addGap(48)
            .addGroup(layout.createParallelGroup()
                .addComponent(modeControlCard)
                .addComponent(windControlCard1)
                .addComponent(windControlCard2)
            )
            .addGap(24)
            .addGroup(layout.createParallelGroup()
                .addComponent(graphicDisplayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
                .addComponent(ppmDisplayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
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

    public void updatePPM(String ppm) {

        ppmDisplayCard.setValue(ppm);

    }

    public void updateView(GreenhouseModel model) {



    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property) {

			case PROPERTY_UPDATE_CO2:

                int ppm = (int)evt.getNewValue();

                updatePPM(Integer.toString(ppm));

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
