package com.greensense.constants;

import java.awt.Font;

import com.greensense.util.FontFactory;

public interface Fonts {

    Font PoppinsRegular = FontFactory.loadFont("fonts/Poppins/Poppins-Regular.ttf");
    Font PoppinsMedium = FontFactory.loadFont("fonts/Poppins/Poppins-Medium.ttf");
    
    // Inter Regular
    Font InterRegular = FontFactory.loadFont("fonts/Inter/Inter-Regular.ttf");
    Font InterRegular_10 = FontFactory.changeFontSize(InterRegular, 10.0f);   // 10px
    Font InterRegular_12 = FontFactory.changeFontSize(InterRegular, 12.0f);   // 12px
    Font InterRegular_14 = FontFactory.changeFontSize(InterRegular, 14.0f);   // 14px
    Font InterRegular_16 = FontFactory.changeFontSize(InterRegular, 16.0f);   // 16px
    Font InterRegular_32 = FontFactory.changeFontSize(InterRegular, 32.0f);   // 32px


    // Inter Medium
    Font InterMedium = FontFactory.loadFont("fonts/Inter/Inter-Medium.ttf");
    Font InterMedium_9 = FontFactory.changeFontSize(InterMedium, 9.0f);     // 9px
    Font InterMedium_16 = FontFactory.changeFontSize(InterMedium, 16.0f);   // 16px
    Font InterMedium_18 = FontFactory.changeFontSize(InterMedium, 18.0f);   // 18px
    Font InterMedium_24 = FontFactory.changeFontSize(InterMedium, 24.0f);   // 24px
    Font InterMedium_32 = FontFactory.changeFontSize(InterMedium, 32.0f);   // 32px
    Font InterMedium_48 = FontFactory.changeFontSize(InterMedium, 48.0f);   // 48px
    Font InterMedium_128 = FontFactory.changeFontSize(InterMedium, 128.0f);   // 128px


    // Inter SemiBold
    Font InterSemiBold = FontFactory.loadFont("fonts/Inter/Inter-SemiBold.ttf");    
    Font InterSemiBold_12 = FontFactory.changeFontSize(InterSemiBold, 12.0f);   // 12px
    Font InterSemiBold_16 = FontFactory.changeFontSize(InterSemiBold, 16.0f);   // 16px
    Font InterSemiBold_24 = FontFactory.changeFontSize(InterSemiBold, 24.0f);   // 24px
    Font InterSemiBold_64 = FontFactory.changeFontSize(InterSemiBold, 64.0f);   // 64px
    

    // Poppins SemiBold
    Font PoppinsSemiBold = FontFactory.loadFont("fonts/Poppins/Poppins-SemiBold.ttf");
    Font PoppinsSemiBold_24 = FontFactory.changeFontSize(PoppinsSemiBold, 24.0f);   // 32px
    Font PoppinsSemiBold_48 = FontFactory.changeFontSize(PoppinsSemiBold, 48.0f);   // 48px
    Font PoppinsSemiBold_64 = FontFactory.changeFontSize(PoppinsSemiBold, 64.0f);   // 64px
    
}
