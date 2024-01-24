package com.greensense.model.alert;

import com.greensense.Palette;
import com.greensense.constants.Images;
import lombok.Getter;

import javax.swing.ImageIcon;
import java.awt.Color;


@Getter
public enum AlertType implements Images {
    SUCCESS("Success", ICON_XL_CHECK, Palette.ALERT_SUCCESS_BG, Palette.ALERT_SUCCESS_BORDER),
    ERROR("Error", ICON_XL_CLOSE, Palette.ALERT_ERROR_BG, Palette.ALERT_ERROR_BORDER),
    WARNING("Warning", ICON_XL_WARNING, Palette.ALERT_WARNING_BG, Palette.ALERT_WARNING_BORDER),
    INFO("Info", ICON_XL_INFO, Palette.ALERT_INFO_BG, Palette.ALERT_INFO_BORDER);

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
