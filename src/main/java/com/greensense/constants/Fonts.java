package com.greensense.constants;

import java.awt.Font;

import com.greensense.util.FontFactory;

public interface Fonts {

    public static final Font PoppinsRegular = FontFactory.loadFont("fonts/Poppins/Poppins-Regular.ttf");
    public static final Font PoppinsMedium = FontFactory.loadFont("fonts/Poppins/Poppins-Medium.ttf");
    
    // Inter Regular
    public static final Font InterRegular = FontFactory.loadFont("fonts/Inter/Inter-Regular.ttf");
    public static final Font InterRegular_10 = FontFactory.changeFontSize(InterRegular, 10.0f);   // 10px
    public static final Font InterRegular_12 = FontFactory.changeFontSize(InterRegular, 12.0f);   // 12px
    public static final Font InterRegular_14 = FontFactory.changeFontSize(InterRegular, 14.0f);   // 14px
    public static final Font InterRegular_16 = FontFactory.changeFontSize(InterRegular, 16.0f);   // 16px
    public static final Font InterRegular_32 = FontFactory.changeFontSize(InterRegular, 32.0f);   // 32px


    // Inter Medium
    public static final Font InterMedium = FontFactory.loadFont("fonts/Inter/Inter-Medium.ttf");
    public static final Font InterMedium_9 = FontFactory.changeFontSize(InterMedium, 9.0f);     // 9px
    public static final Font InterMedium_16 = FontFactory.changeFontSize(InterMedium, 16.0f);   // 16px
    public static final Font InterMedium_18 = FontFactory.changeFontSize(InterMedium, 18.0f);   // 18px
    public static final Font InterMedium_24 = FontFactory.changeFontSize(InterMedium, 24.0f);   // 24px
    public static final Font InterMedium_32 = FontFactory.changeFontSize(InterMedium, 32.0f);   // 32px
    public static final Font InterMedium_48 = FontFactory.changeFontSize(InterMedium, 48.0f);   // 48px
    public static final Font InterMedium_128 = FontFactory.changeFontSize(InterMedium, 128.0f);   // 128px


    // Inter SemiBold
    public static final Font InterSemiBold = FontFactory.loadFont("fonts/Inter/Inter-SemiBold.ttf");    
    public static final Font InterSemiBold_12 = FontFactory.changeFontSize(InterSemiBold, 12.0f);   // 12px
    public static final Font InterSemiBold_16 = FontFactory.changeFontSize(InterSemiBold, 16.0f);   // 16px
    public static final Font InterSemiBold_24 = FontFactory.changeFontSize(InterSemiBold, 24.0f);   // 24px
    public static final Font InterSemiBold_64 = FontFactory.changeFontSize(InterSemiBold, 64.0f);   // 64px
    

    // Poppins SemiBold
    public static final Font PoppinsSemiBold = FontFactory.loadFont("fonts/Poppins/Poppins-SemiBold.ttf");
    public static final Font PoppinsSemiBold_24 = FontFactory.changeFontSize(PoppinsSemiBold, 24.0f);   // 32px
    public static final Font PoppinsSemiBold_48 = FontFactory.changeFontSize(PoppinsSemiBold, 48.0f);   // 48px
    public static final Font PoppinsSemiBold_64 = FontFactory.changeFontSize(PoppinsSemiBold, 64.0f);   // 64px
    
}
