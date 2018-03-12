package com.pwr.game.gui.view.icons;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FieldIcon {

    private final URL DEFAULT_FIELD_ICON_PATH = getClass().getClassLoader().getResource("board-icons/field.png");
    private final URL FINISH_FIELD_ICON_PATH = getClass().getClassLoader().getResource("board-icons/field_finish.png");

    private int x;
    private int y;

    public FieldIcon(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Prints field icon on BoardView
     * @param g Graphics
     * @param last boolean
     * Based on last it decides which icon to print
     */
    public void paintFieldIcon(Graphics g, boolean last) {
        Image image;

        if (!last) {
            image = new ImageIcon(DEFAULT_FIELD_ICON_PATH).getImage();
        } else {
            image = new ImageIcon(FINISH_FIELD_ICON_PATH).getImage();
        }

        g.drawImage(image, x - image.getWidth(null) / 2, y - image.getHeight(null) / 2, null);
    }
}

