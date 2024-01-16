package com.greensense.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.greensense.Palette;
import com.greensense.constants.Fonts;

public class ComponentFactory implements Fonts {

    public enum ButtonSize {
        SMALL(InterSemiBold_12, BorderFactory.createEmptyBorder(6, 12, 6, 12)), 
        LARGE(InterSemiBold_16, BorderFactory.createEmptyBorder(8, 16, 8, 16));

        private Font font;
        private Border border;

        ButtonSize(Font font, Border border){
            this.font = font;
            this.border = border;
        }

        public Font getFont() { return font; }
        public Border getBorder() { return border; }

    };

    public static JLabel createLabel(String text, Color fg, Font font){

        JLabel label = new JLabel(text);

        label.setFont(font);
        label.setForeground(fg);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        return label;

    }

    public static JTextArea createTextArea(String text, Color fg, Font font){

        JTextArea textArea = new JTextArea(text);

        textArea.setFont(font);
        textArea.setForeground(fg);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false); // Make it transparent like a label
        textArea.setFocusable(false);

        return textArea;

    }

    public static JButton createPrimaryButton(AbstractAction action, ButtonSize size){

        JButton button = new JButton(action);

        button.setFont(size.getFont());
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(size.getBorder());
        button.setBackground(Palette.BTN_PRIMARY_BG);
        button.setForeground(Palette.BTN_PRIMARY_FG);

        return button;

    }

    public static JButton createSecondaryButton(AbstractAction action, ButtonSize size){

        JButton button = new JButton(action);

        button.setText("<html><u>" + action.getValue(AbstractAction.NAME) + "</u></html>");
        button.setFont(size.getFont());
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(size.getBorder());
        // button.setBackground(Palette.BTN_PRIMARY_BG);
        button.setForeground(Palette.TEXT_SECONDARY_FG);

        return button;

    }

    public static JButton createIconButton(AbstractAction action, ImageIcon icon){

        JButton button = new JButton(action);

        button.setIcon(icon);

        // button.setFont(size.getFont());
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(null);
        // button.setBackground(Palette.BTN_PRIMARY_BG);
        // button.setForeground(Palette.BTN_PRIMARY_FG);

        return button;

    }

    public static JButton createSectionButton(AbstractAction action){

        JButton button = new JButton(action);

        // button.setIcon(icon);
        button.setFont(InterMedium_24);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Palette.BORDER_BG, 1),
                BorderFactory.createEmptyBorder(24, 8, 24, 8)
            )
        );
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        // button.setBackground(Palette.BTN_PRIMARY_BG);
        button.setForeground(Palette.GREEN_400);

        return button;

    }

    public static <T extends JTextField> JTextField createTextField(Class<T> type){

        JTextField field = new JTextField();

        try {

            field = type.getDeclaredConstructor().newInstance();
            field.setFont(InterMedium_16);
            field.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Palette.BORDER_BG, 1, true), 
                    BorderFactory.createEmptyBorder(0, 16, 0, 0)
                )
            );
            field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
            field.setForeground(Palette.TEXT_PRIMARY_FG);
            field.setAlignmentX(Component.LEFT_ALIGNMENT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //JTextField field = (type.getSimpleName().equals("JPasswordField")) ? new JPasswordField(20) : new JTextField(20);

        return field;

    }

    public static JPanel createInputGroup(JTextField field, String labelText){

        JLabel label = new JLabel(labelText);
        label.setFont(InterMedium_16);
		// TextPrompt textPrompt = new TextPrompt(placeholder, field);

        // textPrompt.setFont(FontFactory.FONT_PoppinsRegular_14);
		// textPrompt.setForeground(Palette.INPUT_PLACEHOLDER);
		// textPrompt.changeAlpha(0.75f);



        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(label)
            .addComponent(field)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(label)
            .addGap(16)
            .addComponent(field, 43, 43, 43)
        );

		return panel;

    }
    
}
