package com.greensense.util;

import java.awt.Color;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

public class JListBuilder<T> {

    private JList<T> list;

    private JListBuilder(){
        this.list = new JList<>();
    }

    public JListBuilder<T> setSelectionBackground(Color background){
        list.setSelectionBackground(background);
        return this;
    }

    public JListBuilder<T> setSelectionForeground(Color foreground){
        list.setSelectionForeground(foreground);
        return this;
    }

    public JListBuilder<T> setSelectionMode(int selectionMode){
        list.setSelectionMode(selectionMode);
        return this;
    }

    public JListBuilder<T> setBackground(Color background){
        list.setBackground(background);
        return this;
    }

    public JListBuilder<T> setBorder(Border border){
        list.setBorder(border);
        return this;
    }

    public JListBuilder<T> setOpaque(boolean opaque){
        list.setOpaque(opaque);
        return this;
    }

    public JListBuilder<T> setLayoutOrientation(int layoutOrientation){
        list.setLayoutOrientation(layoutOrientation);
        return this;
    }

    public JListBuilder<T> setVisibleRowCount(int visibleRowCount){
        list.setVisibleRowCount(visibleRowCount);
        return this;
    }

    public JListBuilder<T> addListSelectionListener(ListSelectionListener listener){
        list.addListSelectionListener(listener);
        return this;
    }

    public JListBuilder<T> withModel(DefaultListModel<T> model) {
        list.setModel(model);
        return this;
    }

    public JListBuilder<T> withModel(DefaultListModel<T> model, Collection<? extends T> data) {
        data.forEach(model::addElement);
        list.setModel(model);
        return this;
    }

    public JListBuilder<T> withRenderer(ListCellRenderer<? super T> renderer) {
        list.setCellRenderer(renderer);
        return this;
    }

    public JList<T> build() {
        return list;
    }

    public static <T> JListBuilder<T> create() {
        return new JListBuilder<T>();
    }

}
