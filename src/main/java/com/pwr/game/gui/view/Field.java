package com.pwr.game.gui.view;

import java.awt.*;

public class Field {
    private int x;
    private int y;
    private int fieldWidth;
    private int fieldHeight;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.fieldWidth = 180;
        this.fieldHeight = 50;
    }

    void paintField(Graphics g, boolean first) {
        Graphics2D g2d = (Graphics2D) g;
        if (first) {
            g2d.drawRoundRect(x, y, fieldWidth, fieldHeight, 15, 15);
        } else {
            g2d.drawOval(x, y, fieldWidth, fieldHeight);
        }
    }
}

