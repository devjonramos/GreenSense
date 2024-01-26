package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.controller.FormHandler;
import com.greensense.util.ActionBuilder;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Form extends JDialog implements Constants, Fonts, ActionListener {

    private FormHandler formHandler;
    private String title;
    private boolean submitted = false;


    private AbstractAction actionAdd, actionCancel;

    private JPanel contentPane;

    private List<FormElement> formElements;

    public Form(JFrame frame, String title, boolean modal, FormHandler formHandler, FormElement... elements){
        super(frame, title, modal);

        this.formElements = List.of(elements);
        this.formHandler = formHandler;
        this.title = title;


        this.createActions();
        this.createContentPane();

        setContentPane(contentPane);
        pack();
        setMinimumSize(new Dimension(555, this.getPreferredSize().height));
        setBackground(Color.WHITE);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    private void createActions(){
        actionAdd = ActionBuilder.createAction("Gehitu", PROPERTY_CONFIRM, this).build();
        actionCancel = ActionBuilder.createAction("Atzera", PROPERTY_CANCEL, this).build();
    }

    private void createContentPane(){

        contentPane = new JPanel();

        GroupLayout layout = new GroupLayout(contentPane);

        JLabel titleLabel = ComponentFactory.createLabel(title, Palette.TEXT_PRIMARY_FG, InterSemiBold_24);

        JButton saveButton = ComponentFactory.createPrimaryButton(actionAdd, ButtonSize.LARGE);
        JButton cancelButton = ComponentFactory.createPrimaryButton(actionCancel, ButtonSize.LARGE);

        ParallelGroup formElementsParallelGroup = layout.createParallelGroup();
        SequentialGroup formElementsSequentialGroup = layout.createSequentialGroup();

        formElements.forEach(formElementsParallelGroup::addComponent);
        formElements.forEach(e -> {
            formElementsSequentialGroup.addComponent(e);
            formElementsSequentialGroup.addGap(16);
        });

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(titleLabel)
                .addGroup(formElementsParallelGroup)
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(8)
                        .addComponent(saveButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(24)
                .addGroup(formElementsSequentialGroup)
                .addGap(24)
                .addGroup(layout.createParallelGroup()
                        .addComponent(cancelButton)
                        .addComponent(saveButton)
                )
        );

        contentPane.setOpaque(false);
        contentPane.setLayout(layout);
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(32, 32, 32, 32)
        );

    }

    public boolean wasSubmitted(){
        return submitted;
    }

    public boolean isFormValid(){

        Map<String, Object> formData = getData();

        for (Object value : formData.values()) {
            if (value.toString().isBlank()) return false;
        }

        return true;

    }

    public Map<String, Object> getData(){

        Map<String, Object> data = new HashMap<>();

        for (FormElement element : formElements) {

            String key = element.getName();
            Object value = element.getFieldValue();

            data.put(key, value);

        }

        return data;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.equals(PROPERTY_CONFIRM)){

            if (isFormValid()) {
                submitted = formHandler.handleFormSubmission(getData(), this);

                if (submitted) dispose();

            }
            else{
                JOptionPane.showMessageDialog(this, "Kanpo guztiak bete behar dira!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else {
            submitted = false;
            dispose();
        }

    }
}
