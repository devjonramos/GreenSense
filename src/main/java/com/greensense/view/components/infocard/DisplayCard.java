package com.greensense.view.components.infocard;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.util.ComponentFactory;

public class DisplayCard extends JPanel implements InfoCard {

    public final Color CARD_BG = Palette.WHITE;

    private JLabel nameLabel;
    private JLabel valueLabel;

    public DisplayCard(String name, String value) {
        
        this.nameLabel = ComponentFactory.createLabel(name, Palette.TEXT_PRIMARY_FG, InterMedium_32);
        this.valueLabel = ComponentFactory.createLabel(value, Palette.TEXT_PRIMARY_FG, InterMedium_128);

        JPanel valuePanel = new JPanel(){
            {

                JLabel unitLabel = ComponentFactory.createLabel("PPM", Palette.TEXT_PRIMARY_FG, InterMedium_32);

                // Configure GridBagConstraints for center alignment
                GridBagConstraints gbc = new GridBagConstraints(){
                    {
                        
                        this.gridx = 0;
                        this.gridy = 0;
                        this.weightx = 1.0; // Expand horizontally
                        this.weighty = 1.0; // Expand vertically
                        this.anchor = GridBagConstraints.CENTER; // Center alignment

                    }
                };


                setLayout(new GridBagLayout());
                setOpaque(false);
                add(valueLabel);
                // add(unitLabel);

            }
        };

        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(nameLabel)
            .addGap(16)
            .addComponent(valuePanel)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(nameLabel)
            .addGap(16)
            .addComponent(valuePanel)
        );

        setLayout(layout);
        setBackground(CARD_BG);
        setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Palette.BLACK_200, 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)    
            )
        );

    }

    public JLabel getValueLabel(){
        return valueLabel;
    }

    public void setValue(String newValue) {
        valueLabel.setText(newValue);
    }

    @Override
    public JPanel getCardPanel() {
        return this;
    }

}
