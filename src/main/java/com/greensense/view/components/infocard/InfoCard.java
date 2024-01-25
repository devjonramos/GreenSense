package com.greensense.view.components.infocard;

import javax.swing.JPanel;

import com.greensense.Palette;
import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;

import java.awt.*;

public interface InfoCard extends Constants, Fonts, Images {

    Color CARD_BG = Palette.WHITE;
    Color BORDER_BG = Palette.BLACK_200;

    JPanel getCardPanel();
    
}
