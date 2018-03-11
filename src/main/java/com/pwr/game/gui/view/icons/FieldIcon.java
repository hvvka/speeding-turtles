package com.pwr.game.gui.view.icons;

import javax.swing.*;
import java.awt.*;

public class FieldIcon {
    private int x;
    private int y;
    private int fieldWidth;
    private int fieldHeight;

    public FieldIcon(int x, int y) {
        this.x = x;
        this.y = y;

        this.fieldWidth = 80;
        this.fieldHeight = 26;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paintField(Graphics g, boolean first) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.lightGray);
        if (first) {
            g2d.fillRoundRect(x - fieldWidth, y - fieldHeight, fieldWidth*2, fieldHeight*2, 15, 15);

        } else {
            g2d.fillOval(x - fieldWidth, y - fieldHeight, fieldWidth*2, fieldHeight*2);
        }
    }

    public void paintFieldIcon(Graphics g, boolean first) {
        Graphics2D g2d = (Graphics2D) g;

        Image image = new ImageIcon("src/main/resources/turtle-icons/field.png").getImage();

        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
        g2d.setColor(Color.lightGray);
    }
}

