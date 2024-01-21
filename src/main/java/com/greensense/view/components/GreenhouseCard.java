package com.greensense.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;
import com.greensense.model.GreenhouseModel;
import com.greensense.util.ActionBuilder;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;

public class GreenhouseCard extends JPanel implements Constants, Images, Fonts {

    private final Color CARD_BG = Palette.WHITE;
    private final Color THUMBNAIL_BG = Palette.GREEN_50;
    private final Color BORDER_COLOR = Palette.BLACK_100;

    public GreenhouseCard(GreenhouseModel greenhouse, ActionListener listener){
        super(new BorderLayout(0, 8));
        
        setBackground(CARD_BG);
        setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 2, 1, BORDER_COLOR),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
            )
        );

        JPanel thumbnail = new JPanel(new BorderLayout()){

            {
                
                JLabel image = new JLabel(IMG_GREENHOUSE);

                add(image, BorderLayout.CENTER);

                setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
                setBackground(THUMBNAIL_BG);

            }

        };

        JPanel content = new JPanel() {

            {

                JLabel name = ComponentFactory.createLabel(greenhouse.getName(), Palette.TEXT_PRIMARY_FG, InterMedium_18);
                JLabel desc = ComponentFactory.createLabel("Mode: " + greenhouse.getMode(), Palette.TEXT_SECONDARY_FG, InterRegular_12);

                AbstractAction actionOpen = ActionBuilder.createAction("Ireki Negutegia", PROPERTY_OPEN_GREENHOUSE, listener).putValue("greenhouseID", greenhouse.getId()).build();
                JButton btnOpen = ComponentFactory.createPrimaryButton(actionOpen, ButtonSize.SMALL);

                AbstractAction actionChangeName = ActionBuilder.createAction("Aldatu Izena", PROPERTY_CHANGE_GREENHOUSE_NAME, listener).putValue("greenhouse", greenhouse).build();
                JButton btnChangeName = ComponentFactory.createSecondaryButton(actionChangeName, ButtonSize.SMALL);


                GroupLayout layout = new GroupLayout(this);

                layout.setHorizontalGroup(layout.createParallelGroup()
                    .addComponent(name)
                    .addComponent(desc)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOpen)
                        .addGap(8)
                        .addComponent(btnChangeName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                );

                layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(name)
                    .addGap(4)
                    .addComponent(desc)
                    .addGap(24)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(btnOpen)
                        .addComponent(btnChangeName)
                    )
                );

                setLayout(layout);
                setOpaque(false);

            }

        };

        add(thumbnail, BorderLayout.CENTER);
        add(content, BorderLayout.SOUTH);

    }

}
