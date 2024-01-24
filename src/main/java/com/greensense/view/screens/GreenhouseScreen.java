package com.greensense.view.screens;

import java.awt.BorderLayout;

import java.beans.PropertyChangeEvent;

import javax.swing.AbstractAction;
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

    private JLabel nameLabel;

    @Getter private ControlCard modeControlCard, fanControlCard1, fanControlCard2;
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

        nameLabel = ComponentFactory.createLabel(greenhouseModel.getName(), Palette.TEXT_PRIMARY_FG, InterMedium_48);

        modeControlCard = new ControlCard("Modua", ICON_MD_TOOL, PROPERTY_TOGGLE_MODE, controller);
        fanControlCard1 = new ControlCard("Haizagailua1", ICON_MD_WIND, PROPERTY_TOGGLE_FAN1, controller);
        fanControlCard2 = new ControlCard("Haizagailua2", ICON_MD_WIND, PROPERTY_TOGGLE_FAN2, controller);
        graphicDisplayCard = new DisplayCard("Grafikoa", "967");
        ppmDisplayCard = new DisplayCard("CO2", "542");

        GroupLayout layout = new GroupLayout(panel);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(nameLabel)
            .addGroup(layout.createSequentialGroup()
                .addComponent(modeControlCard, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addGap(24)
                .addComponent(fanControlCard1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                .addGap(24)
                .addComponent(fanControlCard2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
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
                .addComponent(fanControlCard1)
                .addComponent(fanControlCard2)
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

    public void setGreenhouseModel(GreenhouseModel greenhouseModel) { this.greenhouseModel = greenhouseModel; }

    public void updatePPM(String ppm) {
        ppmDisplayCard.setValue(ppm);
    }
    public void updateName(String name) {
        nameLabel.setText(name);
    }
    public void updateMode(boolean mode){
        modeControlCard.setSelected(mode);
    }

    public void updateFan(int fanId, boolean selected){

        if (fanId == 1) fanControlCard1.setSelected(selected);
        else if (fanId == 2) fanControlCard2.setSelected(selected);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        switch (property) {

			case PROPERTY_UPDATE_GREENHOUSE_PPM:

                int ppm = (int)evt.getNewValue();

                updatePPM(Integer.toString(ppm));

			    break;

            case PROPERTY_UPDATE_GREENHOUSE_NAME:

                String name = (String) evt.getNewValue();

                updateName(name);

                break;

            case PROPERTY_UPDATE_GREENHOUSE_MODE:

                boolean mode = (boolean)evt.getNewValue();

                updateMode(mode);

                break;

            case PROPERTY_UPDATE_GREENHOUSE_FAN_1:

                updateFan(
                        1,
                        (boolean)evt.getNewValue()
                );

                break;

            case PROPERTY_UPDATE_GREENHOUSE_FAN_2:

                updateFan(
                        2,
                        (boolean)evt.getNewValue()
                );

                break;

			default: break;

		}

    }

    @Override
    public void load() {

        controller.setGreenhouseScreen(this);
        controller.setGreenhouseModel(greenhouseModel);
        controller.loadData();
        this.repaint();
        controller.startMQTTService();
    }

    @Override
    public void dispose() {
        controller.stopMQTTService();
    }

}
