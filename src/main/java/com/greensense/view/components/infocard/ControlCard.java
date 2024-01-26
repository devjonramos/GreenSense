package com.greensense.view.components.infocard;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.greensense.Palette;
import com.greensense.util.ComponentFactory;
import com.greensense.view.components.togglebutton.ToggleButton;
import com.greensense.view.components.togglebutton.ToggleButtonListener;
import lombok.Getter;

public class ControlCard extends JPanel implements InfoCard {

    private String title;
    private ImageIcon icon;
    private ControlCard controlCard = this;

    @Getter
    private ToggleButton toggleButton;
    private JLabel titleLabel;

    private ToggleButtonListener listener;

    public ControlCard(String title, ImageIcon icon, String command, ToggleButtonListener listener) {

        this.title = title;
        this.icon = icon;
        this.listener = listener;
        this.toggleButton = new ToggleButton(this.title.toLowerCase());
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

        titleLabel = ComponentFactory.createLabel(this.title, Palette.TEXT_PRIMARY_FG, InterMedium_32);

        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createParallelGroup()
                // .addGroup(layout.createSequentialGroup()
                //     .addComponent(iconLabel)
                //     // .addGap(16, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
                //     .addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                // )
                .addComponent(cardHeader)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
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
                        .addComponent(titleLabel)
                )
        );


        setLayout(layout);
        setBackground(CARD_BG);
        setMaximumSize(new Dimension(215, 173));
        setPreferredSize(new Dimension(215, 173));
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_BG, 1),
                        BorderFactory.createEmptyBorder(16, 16, 16, 16)
                )
        );

    }

    public void setToggleEnabled(boolean enabled){
        toggleButton.setEnabled(enabled);
    }

    public void setTitle(String title) {
        this.title = title;
        titleLabel.setText(this.title);
    }

    public void setSelected(boolean selected){

        toggleButton.setSelected(selected);

    }
    @Override
    public JPanel getCardPanel() {
        return this;
    }



}
