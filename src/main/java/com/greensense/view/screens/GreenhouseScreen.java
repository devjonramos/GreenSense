package com.greensense.view.screens;

import com.greensense.Palette;
import com.greensense.controller.GreenhouseController;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.ActionBuilder;
import com.greensense.util.BorderCreator;
import com.greensense.util.ComponentFactory;
import com.greensense.view.components.Header;
import com.greensense.view.components.infocard.ControlCard;
import com.greensense.view.components.infocard.DisplayCard;
import com.greensense.view.components.infocard.GraphicCard;
import lombok.Getter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.util.Random;

public class GreenhouseScreen extends JPanel implements Screen {

    private final GreenhouseController controller;

    private GreenhouseModel model;

    private AbstractAction actionNext, actionPrevious;

    private JLabel nameLabel;

    private XYSeries ppmSeries;

    @Getter private ControlCard modeControlCard, fanControlCard1, fanControlCard2;
    @Getter private DisplayCard ppmDisplayCard, graphicDisplayCard;
    private GraphicCard graphicCard;

    public GreenhouseScreen(GreenhouseModel greenhouseModel) {

        this.setModel(greenhouseModel);

        controller = new GreenhouseController(this, this.model);
        this.createActions();

        setLayout(new BorderLayout(0, 0));
        setBackground(Palette.MAIN_BG);

        JToolBar header = createHeader();
        JPanel content = createContentPanel();

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

    }

    private void createActions() {
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

        nameLabel = ComponentFactory.createLabel(model.getName(), Palette.TEXT_PRIMARY_FG, InterMedium_48);

        modeControlCard = new ControlCard("Mode: " + model.getModeName(), ICON_MD_TOOL, PROPERTY_TOGGLE_MODE, controller);
        fanControlCard1 = new ControlCard("Haizea kanpora", ICON_MD_WIND, PROPERTY_TOGGLE_FAN1, controller);
        fanControlCard2 = new ControlCard("Haizea barrura", ICON_MD_WIND, PROPERTY_TOGGLE_FAN2, controller);
        graphicDisplayCard = new DisplayCard("Grafikoa", "967");
        ppmDisplayCard = new DisplayCard("CO2", "0");
        graphicCard = new GraphicCard("Grafikoa", createGraphic());

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
                .addComponent(graphicCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 438)
                .addGap(24)
                .addComponent(ppmDisplayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 438)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(nameLabel)
            .addGap(32)
            .addGroup(layout.createParallelGroup()
                .addComponent(modeControlCard)
                .addComponent(fanControlCard1)
                .addComponent(fanControlCard2)
            )
            .addGap(24)
            .addGroup(layout.createParallelGroup()
                .addComponent(graphicCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 411)
                .addComponent(ppmDisplayCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 411)
            )
            
        );

        panel.setLayout(layout);
        panel.setOpaque(false);
        panel.setBorder(BorderCreator.createEmptyBorder(64, 16));

        return panel;

    }

    public ChartPanel createGraphic(){

        ppmSeries = new XYSeries("CO2 PPM");
        Random random = new Random();
        for (int time = 1; time <= 20; time++) {
            ppmSeries.add(time, random.nextInt(1000));
        }

        XYSeriesCollection dataset = new XYSeriesCollection(ppmSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "CO2 Sensor Data Over Time",
                "Denbora",
                "CO2 PPM",
                dataset
        );

        return new ChartPanel(chart);

    }

    public void setModel(GreenhouseModel greenhouseModel) { this.model = greenhouseModel; }

    public void setFanControlCardEnabled(int fanId, boolean enabled) {

        if (fanId == 1) fanControlCard1.getToggleButton().setEnabled(enabled);
        else if (fanId == 2) fanControlCard2.getToggleButton().setEnabled(enabled);

    }

    public void updatePPM(String ppm) {
        ppmDisplayCard.setValue(ppm);
    }
    public void updateName(String name) {
        nameLabel.setText(name);
    }
    public void updateMode(boolean mode){
        //modeControlCard.setName((mode) ? "AUTO" : "MAN");
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

                if (mode){
                    setFanControlCardEnabled(1, false);
                    setFanControlCardEnabled(2, false);
                }

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
        controller.setGreenhouseModel(model);
        controller.loadData();
        controller.startMQTTService();
    }

    @Override
    public void dispose() {
        controller.stopMQTTService();
    }

}
