package com.pwr.game.gui.view.icons;

import javax.swing.*;
import java.awt.*;

public class FieldIcon {

    private static final String DEFAULT_FIELD_ICON_PATH = "src/main/resources/board-icons/field.png";
    private static final String FINISH_FIELD_ICON_PATH = "src/main/resources/board-icons/field_finish.png";

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

    public void paintFieldIcon(Graphics g, boolean last) {
        Image image;

        if (!last) {
            image = new ImageIcon(DEFAULT_FIELD_ICON_PATH).getImage();
        } else {
            image = new ImageIcon(FINISH_FIELD_ICON_PATH).getImage();
        }

        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
    }
}

