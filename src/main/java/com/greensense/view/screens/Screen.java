package com.greensense.view.screens;

import java.beans.PropertyChangeListener;

import com.greensense.constants.Constants;
import com.greensense.constants.Fonts;
import com.greensense.constants.Images;

public interface Screen extends Constants, Fonts, Images, PropertyChangeListener {

    public void load();
    public void dispose();

}
