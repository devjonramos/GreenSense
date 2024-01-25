package com.greensense.view.components.infocard;

import com.greensense.Palette;
import com.greensense.util.ComponentFactory;
import org.jfree.chart.ChartPanel;

import javax.swing.*;

public class GraphicCard extends JPanel implements InfoCard{

    private JLabel nameLabel;
    private ChartPanel graphic;

    public GraphicCard(String name, ChartPanel graphic){
        this.nameLabel = ComponentFactory.createLabel(name, Palette.TEXT_PRIMARY_FG, InterMedium_32);
        this.graphic = graphic;

        this.setUI();

    }

    public void setUI(){

        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(graphic)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(graphic)
        );

        setLayout(layout);
        setBackground(CARD_BG);
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_BG, 1),
                        BorderFactory.createEmptyBorder(16, 16, 16, 16)
                )
        );

    }

    @Override
    public JPanel getCardPanel() {
        return null;
    }
}
