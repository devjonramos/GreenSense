package com.greensense.view.components.togglebutton;

import java.awt.Component;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.greensense.Palette;
import lombok.Getter;

public class ToggleButton extends Component{

    public final int WIDTH = 50;
    public final int HEIGHT = 25;

    public final Color COLOR_TOGGLE_OFF = Palette.BLACK_50;
    public final Color COLOR_TOGGLE_ON = Palette.GREEN_400;

    private String name;
    private Timer timer;
    private float location;

    @Getter private boolean selected;
    private boolean mouseOver;
    private float speed = 0.1f;
    private ToggleEvent toggleEvent;
    private List<ToggleButtonListener> listeners;

    private int ticks = 0;

    public ToggleButton(String name) {

        setBackground(COLOR_TOGGLE_ON); // new Color(0, 174, 255)
        setForeground(Color.WHITE);
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.name = name;
        this.location = 2;
        this.toggleEvent = new ToggleEvent(this, "", false);
        this.listeners = new ArrayList<>();

        timer = new Timer(1, (ActionEvent ae) -> {

            if (isSelected()) {

                int endLocation = getWidth() - getHeight() + 2;
                if (location < endLocation) {
                    location += speed;
                } else {
                    timer.stop();
                    location = endLocation;
                }

            } else {

                int endLocation = 2;
                if (location > endLocation) {
                    location -= speed;
                } else {
                    timer.stop();
                    location = endLocation;
                }

            }

            if (ticks >= 10) {
                repaint();
                ticks = 0;
            }

            ticks++;

        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {

                if (SwingUtilities.isLeftMouseButton(me) && isEnabled()) {
                    if (mouseOver) {
                        selected = !selected;
                        toggleEvent.setSelected(selected);
                        timer.start();
                        runEvent();
                    }
                }

            }

        });
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        timer.start();
        runEvent();
    }

    public void setToggleCommand(String command){
        this.toggleEvent.setToggleCommand(command);
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        float alpha = getAlpha();

        if (alpha < 1) {
            // g2.setColor(Color.GRAY);
            g2.setColor(COLOR_TOGGLE_OFF);
            g2.fillRoundRect(0, 0, width, height, 25, 25);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 25, 25);
        g2.setColor(getForeground());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillOval((int) location, 2, height - 4, height - 4);

        super.paint(g);

    }

    private float getAlpha() {

        float width = getWidth() - getHeight();
        float alpha = (location - 2) / width;

        if (alpha < 0) {
            alpha = 0;
        }
        else if (alpha > 1) {
            alpha = 1;
        }

        return alpha;

    }

    private void runEvent() {
        for (ToggleButtonListener listener : listeners) {
            listener.onToggle(toggleEvent);
        }
    }

    public void addToggleListener(ToggleButtonListener listener) {
        listeners.add(listener);
    }

}
