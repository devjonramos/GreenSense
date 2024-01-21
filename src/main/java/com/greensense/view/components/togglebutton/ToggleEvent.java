package com.greensense.view.components.togglebutton;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ToggleEvent {

    private Object source;
    @Setter private boolean selected;
    @Setter private String toggleCommand;

    public ToggleEvent(Object source, String command, boolean selected) {

        this.source = source;
        this.toggleCommand = command;
        this.selected = selected;

    }

}
