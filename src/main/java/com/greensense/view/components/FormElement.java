package com.greensense.view.components;

import lombok.Getter;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;


@Getter
public class FormElement extends JPanel {

    private JLabel fieldLabel;
    private JComponent field;

    public FormElement(JLabel label, JComponent fieldComponent) {

        this.fieldLabel = label;
        this.field = fieldComponent;

        setOpaque(false);
        setLayout(new GridLayout(1, 2, 8, 8));
        setMinimumSize(new Dimension(this.getPreferredSize().width, fieldComponent.getPreferredSize().height));

        add(fieldLabel);
        add(field);

    }

    public Object getFieldValue(){

        Object value = null;

        if(this.field instanceof JTextField) value = ((JTextField) this.field).getText();
        else if (this.field instanceof JComboBox) value = ((JComboBox) this.field).getSelectedItem().toString();
        
        return value;

    }

}
