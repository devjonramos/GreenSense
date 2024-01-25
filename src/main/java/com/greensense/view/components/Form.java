package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.util.ActionBuilder;
import com.greensense.util.ComponentFactory;
import com.greensense.util.ComponentFactory.ButtonSize;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JDialog implements Constants, Fonts, ActionListener {

    private String title;

    private AbstractAction actionAdd, actionCancel;

    private JPanel contentPane;

    private FormElement name;
    private FormElement surname;

    public Form(JFrame frame, String title, boolean modal){
        super(frame, title, modal);

        this.title = title;

        this.createActions();
        this.createContentPane();

        setContentPane(contentPane);
        pack();
        setMinimumSize(new Dimension(555, this.getPreferredSize().height));
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    private void createActions(){
        actionAdd = ActionBuilder.createAction("Gehitu", PROPERTY_ADD_GREENHOUSE, this).build();
        actionCancel = ActionBuilder.createAction("Cancel", PROPERTY_CANCEL, this).build();
    }

    private void createContentPane(){

        contentPane = new JPanel();

        GroupLayout layout = new GroupLayout(contentPane);

        JLabel titleLabel = ComponentFactory.createLabel(title, Palette.TEXT_PRIMARY_FG, InterSemiBold_24);

        JLabel nameLabel = ComponentFactory.createLabel("Name", Palette.TEXT_PRIMARY_FG, InterMedium_18);
        JLabel surnameLabel = ComponentFactory.createLabel("Surname", Palette.TEXT_PRIMARY_FG, InterMedium_18);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel roleLabel = new JLabel("Role:");

        // Text Fields
        JTextField nameField = ComponentFactory.createTextField(JTextField.class);
        JTextField surnameField = ComponentFactory.createTextField(JTextField.class);

        name = new FormElement(nameLabel, nameField);
        surname = new FormElement(surnameLabel, surnameField);

        JPanel formFieldsPanel = new JPanel(new GridLayout(2,2, 8,8)){{

            setOpaque(false);

            add(nameLabel);
            add(nameField);
            add(surnameLabel);
            add(surnameField);

        }};

        JButton saveButton = ComponentFactory.createPrimaryButton(actionAdd, ButtonSize.LARGE);
        JButton cancelButton = ComponentFactory.createPrimaryButton(actionCancel, ButtonSize.LARGE);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup()
                        .addComponent(name)
                        .addComponent(surname)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addGap(8)
                        .addComponent(cancelButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(24)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(name)
                        .addComponent(surname)
                )
                .addGap(24)
                .addGroup(layout.createParallelGroup()
                        .addComponent(saveButton)
                        .addComponent(cancelButton)
                )
        );

        contentPane.setOpaque(false);
        contentPane.setLayout(layout);
        // contentPane.setSize(new Dimension(555, 384));
        // contentPane.setPreferredSize(new Dimension(555, 384));
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(32, 32, 32, 32)
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
