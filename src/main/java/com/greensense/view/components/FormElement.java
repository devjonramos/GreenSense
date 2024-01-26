package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.util.ComponentFactory;
import lombok.Getter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;


@Getter
public class FormElement extends JPanel implements Fonts {

    private String name;

    private JLabel fieldLabel;
    private JComponent field;

    public FormElement(Builder builder) {

        this.name = builder.name;
        this.fieldLabel = builder.fieldLabel;
        this.fieldLabel.setMinimumSize(new Dimension(186, this.fieldLabel.getPreferredSize().height));

        this.field = builder.field;

        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(fieldLabel)
                .addComponent(field)
        );

        layout.setVerticalGroup(layout.createParallelGroup(Alignment.CENTER)
                .addComponent(fieldLabel)
                .addComponent(field)
        );

        setOpaque(false);
        setLayout(layout);
        //setMinimumSize(new Dimension(this.getPreferredSize().width, fieldComponent.getPreferredSize().height));

        add(fieldLabel);
        add(field);

    }

    public static class Builder {

        private String name;
        private JLabel fieldLabel;
        private JComponent field;

        public Builder(String name, String label){
            this.name = name;
            this.fieldLabel = ComponentFactory.createLabel(label, Palette.TEXT_PRIMARY_FG, InterMedium_18);
            this.field = ComponentFactory.createTextField(JTextField.class);
        }

        public Builder withTextField(){
            this.field = ComponentFactory.createTextField(JTextField.class);
            return this;
        }

        public <T> Builder withComboBox(T[] data){
            this.field = new JComboBox<>(data);
            return this;
        }

        public FormElement build(){
            return new FormElement(this);
        }

    }

    public Object getFieldValue(){

        Object value = null;

        if(this.field instanceof JTextField) value = ((JTextField) this.field).getText();
        else if (this.field instanceof JComboBox) value = ((JComboBox) this.field).getSelectedItem();
        
        return value;

    }

}
