package com.greensense.model;

import com.greensense.Palette;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public enum AlertType {
    SUCCESS("Success", null, Palette.GREEN_50, Palette.GREEN_400);

    private final String title;
    private final ImageIcon icon;
    private final Color primaryColor;
    private final Color secondaryColor;

    AlertType(String title, ImageIcon icon, Color primaryColor, Color secondaryColor) {
        this.title = title;
        this.icon = icon;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

}
