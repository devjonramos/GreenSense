package com.greensense.constants;

import javax.swing.ImageIcon;

import com.greensense.util.ImageLoader;

public interface Images {

    // IMAGES
	ImageIcon IMG_GREENHOUSE = ImageLoader.loadImageIcon("images/greenhouse.png");
    ImageIcon IMG_GREENHOUSE_BG = ImageLoader.loadImageIcon("images/greenhouse-image.jpg");

    
    // ICONS

    // Small
    int ICON_SM_SIZE = 24;
    ImageIcon ICON_SM_PREVIOUS = ImageLoader.loadImageIcon("icons/sm/ti-chevron-left.png");
    ImageIcon ICON_SM_NEXT = ImageLoader.loadImageIcon("icons/sm/ti-chevron-right.png");
    ImageIcon ICON_SM_BACK = ImageLoader.loadImageIcon("icons/sm/ti-arrow-narrow-left.png");
    ImageIcon ICON_SM_EDIT = ImageLoader.loadImageIcon("icons/sm/ti-edit-3.png");
    ImageIcon ICON_SM_PLUS = ImageLoader.loadImageIcon("icons/sm/ti-plus.png");
    ImageIcon ICON_SM_LOGOUT = ImageLoader.loadImageIcon("icons/sm/ti-power.png");
    ImageIcon ICON_SM_SETTINGS = ImageLoader.loadImageIcon("icons/sm/ti-settings.png");

    // Medium
    int ICON_MD_SIZE = 32;
    ImageIcon ICON_MD_DROPLET = ImageLoader.loadImageIcon("icons/md/ti-droplet.png");
    ImageIcon ICON_MD_SEEDING = ImageLoader.loadImageIcon("icons/md/ti-seeding.png");
    ImageIcon ICON_MD_TOOL = ImageLoader.loadImageIcon("icons/md/ti-tool.png");
    ImageIcon ICON_MD_WIND = ImageLoader.loadImageIcon("icons/md/ti-wind.png");

    // Large
    int ICON_LG_SIZE = 48;
    ImageIcon ICON_LG_ALERT = ImageLoader.loadImageIcon("icons/lg/ti-alert.png");
    ImageIcon ICON_LG_TEMPERATURE = ImageLoader.loadImageIcon("icons/lg/ti-temperature.png");

    // Extra Large
    int ICON_XL_SIZE = 64;
    ImageIcon ICON_XL_STATISTICS = ImageLoader.loadImageIcon("icons/xl/ti-chart-bar.png");
    ImageIcon ICON_XL_GRID = ImageLoader.loadImageIcon("icons/xl/ti-layout-grid.png");
    ImageIcon ICON_XL_LOG = ImageLoader.loadImageIcon("icons/xl/ti-notes.png");
    ImageIcon ICON_XL_SETTINGS = ImageLoader.loadImageIcon("icons/xl/ti-settings.png");
    ImageIcon ICON_XL_USERS = ImageLoader.loadImageIcon("icons/xl/ti-users.png");
    ImageIcon ICON_XL_CHECK = ImageLoader.loadImageIcon("icons/xl/ti-check.png");
    ImageIcon ICON_XL_CLOSE = ImageLoader.loadImageIcon("icons/xl/ti-close.png");
    ImageIcon ICON_XL_WARNING = ImageLoader.loadImageIcon("icons/xl/ti-warning.png");
    ImageIcon ICON_XL_INFO = ImageLoader.loadImageIcon("icons/xl/ti-info.png");


}