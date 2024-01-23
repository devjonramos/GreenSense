package com.greensense.model;

import com.greensense.Palette;
import com.greensense.constants.Images;
import lombok.Getter;

import javax.swing.ImageIcon;
import java.awt.Color;


@Getter
public enum AlertType implements Images {
    SUCCESS("Success", ICON_XL_CHECK, Palette.GREEN_50, Palette.GREEN_400);

    private final String title;
    private final ImageIcon icon;
    private final Color backgroundColor;
    private final Color borderColor;

    AlertType(String title, ImageIcon icon, Color backgroundColor, Color borderColor) {
        this.title = title;
        this.icon = icon;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

}
