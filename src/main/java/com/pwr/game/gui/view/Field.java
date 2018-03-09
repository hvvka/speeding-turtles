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

        this.fieldWidth = 90;
        this.fieldHeight = 26;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void paintField(Graphics g, boolean first) {
        Graphics2D g2d = (Graphics2D) g;
        if (first) {
            g2d.drawRoundRect(x - fieldWidth, y - fieldHeight, fieldWidth*2, fieldHeight*2, 15, 15);

        } else {
            g2d.drawOval(x - fieldWidth, y - fieldHeight, fieldWidth*2, fieldHeight*2);
        }
    }
}

