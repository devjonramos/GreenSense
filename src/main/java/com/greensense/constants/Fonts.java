package com.greensense.constants;

import java.awt.Font;

import com.greensense.util.FontLoader;

public interface Fonts {

    Font PoppinsRegular = FontLoader.loadFont("fonts/Poppins/Poppins-Regular.ttf");
    Font PoppinsMedium = FontLoader.loadFont("fonts/Poppins/Poppins-Medium.ttf");
    
    // Inter Regular
    Font InterRegular = FontLoader.loadFont("fonts/Inter/Inter-Regular.ttf");
    Font InterRegular_10 = FontLoader.changeFontSize(InterRegular, 10.0f);   // 10px
    Font InterRegular_12 = FontLoader.changeFontSize(InterRegular, 12.0f);   // 12px
    Font InterRegular_14 = FontLoader.changeFontSize(InterRegular, 14.0f);   // 14px
    Font InterRegular_16 = FontLoader.changeFontSize(InterRegular, 16.0f);   // 16px
    Font InterRegular_32 = FontLoader.changeFontSize(InterRegular, 32.0f);   // 32px


    // Inter Medium
    Font InterMedium = FontLoader.loadFont("fonts/Inter/Inter-Medium.ttf");
    Font InterMedium_9 = FontLoader.changeFontSize(InterMedium, 9.0f);     // 9px
    Font InterMedium_14 = FontLoader.changeFontSize(InterMedium, 14.0f);   // 14px
    Font InterMedium_16 = FontLoader.changeFontSize(InterMedium, 16.0f);   // 16px
    Font InterMedium_18 = FontLoader.changeFontSize(InterMedium, 18.0f);   // 18px
    Font InterMedium_24 = FontLoader.changeFontSize(InterMedium, 24.0f);   // 24px
    Font InterMedium_32 = FontLoader.changeFontSize(InterMedium, 32.0f);   // 32px
    Font InterMedium_48 = FontLoader.changeFontSize(InterMedium, 48.0f);   // 48px
    Font InterMedium_128 = FontLoader.changeFontSize(InterMedium, 128.0f);   // 128px


    // Inter SemiBold
    Font InterSemiBold = FontLoader.loadFont("fonts/Inter/Inter-SemiBold.ttf");
    Font InterSemiBold_12 = FontLoader.changeFontSize(InterSemiBold, 12.0f);   // 12px
    Font InterSemiBold_16 = FontLoader.changeFontSize(InterSemiBold, 16.0f);   // 16px
    Font InterSemiBold_24 = FontLoader.changeFontSize(InterSemiBold, 24.0f);   // 24px
    Font InterSemiBold_64 = FontLoader.changeFontSize(InterSemiBold, 64.0f);   // 64px
    

    // Poppins SemiBold
    Font PoppinsSemiBold = FontLoader.loadFont("fonts/Poppins/Poppins-SemiBold.ttf");
    Font PoppinsSemiBold_24 = FontLoader.changeFontSize(PoppinsSemiBold, 24.0f);   // 32px
    Font PoppinsSemiBold_48 = FontLoader.changeFontSize(PoppinsSemiBold, 48.0f);   // 48px
    Font PoppinsSemiBold_64 = FontLoader.changeFontSize(PoppinsSemiBold, 64.0f);   // 64px
    
}
