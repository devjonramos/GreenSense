package com.greensense.view.components;

import com.greensense.Palette;
import com.greensense.constants.Fonts;
import com.greensense.util.ComponentFactory;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.FlowLayout;

public class UsersTable extends JTable implements Fonts {

    public UsersTable(DefaultTableModel tableModel) {
        super(tableModel);

        setFont(InterSemiBold_16);
        setForeground(Palette.TEXT_PRIMARY_FG);
        setBorder(
                BorderFactory.createMatteBorder(0, 1, 1, 1, Palette.GREEN_400)
        );
        setRowHeight(51);
        setGridColor(Palette.GREEN_100);
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component cellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                        // Set padding for the cell
                        setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));

                        return cellRendererComponent;
                    }

                }

        );

        getTableHeader().setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 0, 1, Palette.GREEN_400),
                        BorderFactory.createEmptyBorder(16, 16, 16, 16)
                )
        );

        getTableHeader().setDefaultRenderer((jtable, value, isSelected, hasFocus, row, column) -> {

            JPanel cellPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
                setBackground(Palette.GREEN_100);
                setBorder(
                        BorderFactory.createEmptyBorder(16, 16, 16, 16)
                );
            }};

            JLabel cellLabel = ComponentFactory.createLabel(value.toString(), Palette.TEXT_PRIMARY_FG, InterSemiBold_16);

            cellPanel.add(cellLabel);

            return cellPanel;

        });

    }

}
