package com.greensense;

import java.awt.Color;

public class Palette{

    // REFERENCE
    public static final Color EMERALD_GREEN = Color.decode("#3BBF7A");
    public static final Color DARK_GRAY = Color.decode("#272F2B");
    public static final Color LIGHT_GRAY = Color.decode("#C7C7C7");
    public static final Color WHITE = Color.decode("#FFFFFF");
    public static final Color WHITE_SMOKE = Color.decode("#FBFBFB");
    public static final Color FROSTY_GREEN = Color.decode("#F1F9F5");
    public static final Color DARK_YELLOW = Color.decode("#DF9C20");

    public static final Color PINK_50 = Color.decode("#FDEEEE");
    public static final Color PINK_400 = Color.decode("#EC8087");

    public static final Color GREEN_50 = Color.decode("#eefbf3");
    public static final Color GREEN_100 = Color.decode("#d5f6e0");
    public static final Color GREEN_200 = Color.decode("#aeecc6");
    public static final Color GREEN_300 = Color.decode("#79dca6");
    public static final Color GREEN_400 = Color.decode("#3bbf7b");
    public static final Color GREEN_500 = Color.decode("#1faa67");
    public static final Color GREEN_600 = Color.decode("#128952");
    public static final Color GREEN_700 = Color.decode("#0e6e45");
    public static final Color GREEN_800 = Color.decode("#0e5737");
    public static final Color GREEN_900 = Color.decode("#0c482f");
    public static final Color GREEN_950 = Color.decode("#06281b");

    public static final Color BLACK_50 = Color.decode("#f6f7f7");
    public static final Color BLACK_100 = Color.decode("#e0e7e3");
    public static final Color BLACK_200 = Color.decode("#c0cfc6");
    public static final Color BLACK_300 = Color.decode("#99afa1");
    public static final Color BLACK_400 = Color.decode("#748d7f");
    public static final Color BLACK_500 = Color.decode("#597365");
    public static final Color BLACK_600 = Color.decode("#465b50");
    public static final Color BLACK_700 = Color.decode("#3b4a43");
    public static final Color BLACK_800 = Color.decode("#323d38");
    public static final Color BLACK_900 = Color.decode("#272f2b");
    public static final Color BLACK_950 = Color.decode("#161d1a");


    // SYSTEM
    public static final Color MAIN_BG = WHITE;
    public static final Color LOGO_BG = GREEN_400;
    public static final Color BORDER_BG = LIGHT_GRAY;

    public static final Color TEXT_PRIMARY_FG = BLACK_900;
    public static final Color TEXT_SECONDARY_FG = BLACK_300;
    public static final Color TEXT_ACCENT_FG = BLACK_400;
    
    public static final Color BTN_PRIMARY_BG = GREEN_400;
    public static final Color BTN_PRIMARY_FG = WHITE;
    
    public static final Color INPUT_BORDER_BG = BLACK_300;

    // public static final Color GREENHOUSE_CARD_BG = FROSTY_GREEN;
    public static final Color MODE_AUTO_BG = EMERALD_GREEN;
    public static final Color MODE_MAN_BG = DARK_YELLOW;

    public static final Color INFO_CARD_BG = WHITE;


    // Alerts
    public static final Color ALERT_SOURCE_FG = Color.decode("#2B82E8");
    public static final Color ALERT_SUCCESS_BG = Color.decode("#E6FAF5");
    public static final Color ALERT_SUCCESS_BORDER = Color.decode("#00CC99");
    public static final Color ALERT_ERROR_BG = Color.decode("#FDEEEE");
    public static final Color ALERT_ERROR_BORDER = Color.decode("#EB5757");
    public static final Color ALERT_WARNING_BG = Color.decode("#FDF8E8");
    public static final Color ALERT_WARNING_BORDER = Color.decode("#F2C94C");
    public static final Color ALERT_INFO_BG = Color.decode("#EEEEFE");
    public static final Color ALERT_INFO_BORDER = Color.decode("#5458F7");

    public static Color HEXtoRGB(Color hex, float opacity){
        return new Color(hex.getRed(), hex.getGreen(), hex.getBlue(), (int) (opacity * 255));
    }

    public static Color darker(Color hex, double factor) {
        return new Color((int) (hex.getRed() * factor), (int) (hex.getGreen() * factor), (int) (hex.getBlue() * factor), hex.getAlpha());
    }

}
