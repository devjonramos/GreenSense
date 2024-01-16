package com.greensense.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageLoader {

    public static ImageIcon loadImageIcon(String imagePath) {

        ImageIcon image = null;

        try {
        
            URL url = ImageLoader.class.getClassLoader().getResource(imagePath);

            if (url == null) {
                System.out.println("Image not found at path: " + imagePath);
                return null;
            }

            image = new ImageIcon(url);
        
        } catch (Exception e) {
            
            e.printStackTrace();

            return null;
        }

        return image;

    }

}
