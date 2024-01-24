package com.greensense.util;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    private static final float SIZE_FACTOR = 1f;

    public static Font loadFont(String fontFilePath, int fontFormat, float fontSize) {
     
        Font font = Font.getFont("SansSerif");

        try {
            File fontFile = new File(fontFilePath);
            font = Font.createFont(fontFormat, fontFile);
        } catch (Exception e) {
            System.out.println(e);
        }

        return font.deriveFont((float) (fontSize / 1.3333));

    }

    public static Font loadFontFromResource(String fontResourcePath, int fontFormat, float fontSize) {
        
        Font font = Font.getFont("SansSerif");

        try (InputStream inputStream = FontLoader.class.getClassLoader().getResourceAsStream(fontResourcePath)) {

            if (inputStream != null) {
                font = Font.createFont(fontFormat, inputStream);
            } else {
                throw new IOException("Font resource not found: " + fontResourcePath);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return font.deriveFont((float) (fontSize / 1.3333));
    }

    public static Font loadFont(String fontFilePath) {
        return loadFontFromResource(fontFilePath, Font.TRUETYPE_FONT, 16f);
        // return loadFont(fontFilePath, Font.TRUETYPE_FONT, 16f);
    }

    public static Font changeFontSize(Font font, float fontSize){
        return font.deriveFont(pixelsToPoints(fontSize));
    }

    private static float pixelsToPoints(float pixels) {
        return pixels / SIZE_FACTOR;
    }

}
