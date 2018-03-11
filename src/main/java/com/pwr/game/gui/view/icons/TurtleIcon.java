package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Turtle;

import java.awt.*;

public class TurtleIcon {
    private int x;
    private int y;
    private int fieldWidth;
    private int fieldHeight;

    public TurtleIcon(int x, int y) {
        this.x = x;
        this.y = y;
        this.fieldWidth = 20;
        this.fieldHeight = 16;
    }

    public void paintTurtle(Graphics g, Turtle turtle) {
        Graphics2D g2d = (Graphics2D) g;

        switch (turtle) {
            case YELLOW:
                g2d.setColor(Color.YELLOW);
                break;

            case BLUE:
                g2d.setColor(Color.BLUE);
                break;

            case RED:
                g2d.setColor(Color.RED);
                break;

            case GREEN:
                g2d.setColor(Color.GREEN);
                break;

            case PURPLE:
                g2d.setColor(Color.MAGENTA);
                break;

            default:
                throw new AssertionError("Unknown operations ");
        }
        g2d.fillOval(x - fieldWidth, y - fieldHeight, fieldWidth*2, fieldHeight*2);

    }
}
