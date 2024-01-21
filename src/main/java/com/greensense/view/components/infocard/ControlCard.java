package com.greensense.view.components.infocard;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.util.ComponentFactory;
import com.greensense.view.components.togglebutton.ToggleButton;
import com.greensense.view.components.togglebutton.ToggleButtonListener;

public class ControlCard extends JPanel implements InfoCard {

    public final Color CARD_BG = Palette.WHITE;

    private String name;
    private ImageIcon icon;
    private ControlCard controlCard = this;

    private ToggleButton toggleButton;

    private ToggleButtonListener listener;

    public ControlCard(String name, ImageIcon icon, String command, ToggleButtonListener listener) {

        this.name = name;
        this.icon = icon;
        this.listener = listener;
        this.toggleButton = new ToggleButton(this.name.toLowerCase());
        this.toggleButton.addToggleListener(listener);
        this.toggleButton.setToggleCommand(command);

        setUI();

    }

    private void setUI(){

        JPanel cardHeader = new JPanel(){{

            BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);

            JLabel iconLabel = new JLabel(icon);

            setLayout(layout);
            setOpaque(false);
            add(iconLabel);
            add(Box.createHorizontalGlue());
            add(toggleButton);

        }};

        JLabel nameLabel = ComponentFactory.createLabel(this.name, Palette.TEXT_PRIMARY_FG, InterMedium_32);

        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createParallelGroup()
                // .addGroup(layout.createSequentialGroup()
                //     .addComponent(iconLabel)
                //     // .addGap(16, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                //     .addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                // )
                .addComponent(cardHeader)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                // .addGroup(layout.createParallelGroup(Alignment.CENTER)
                //     .addComponent(iconLabel)
                //     .addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                // )
                .addComponent(cardHeader)
                .addGap(16)
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameLabel)
                )
        );


        setLayout(layout);
        setBackground(CARD_BG);
        setMaximumSize(new Dimension(215, 173));
        setPreferredSize(new Dimension(215, 173));
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Palette.BLACK_200, 1),
                        BorderFactory.createEmptyBorder(16, 16, 16, 16)
                )
        );

    }

    @Override
    public JPanel getCardPanel() {
        return this;
    }

}
