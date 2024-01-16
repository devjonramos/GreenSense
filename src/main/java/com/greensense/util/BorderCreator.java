package com.greensense.util;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class BorderCreator {
    
    public static Border createEmptyBorder(int top, int right, int bottom, int left) {
        return BorderFactory.createEmptyBorder(top, left, bottom, right);
    }

    public static Border createEmptyBorder(int top, int horizontal, int bottom) {
        return createEmptyBorder(top, horizontal, bottom, horizontal);
    }

    public static Border createEmptyBorder(int horizontal, int vertical) {
        return createEmptyBorder(vertical, horizontal, vertical, horizontal);
    }

    public static Border createEmptyBorder(int size) {
        return createEmptyBorder(size, size, size, size);
    }

}
