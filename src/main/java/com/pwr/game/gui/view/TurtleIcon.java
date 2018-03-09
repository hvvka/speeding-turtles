package com.pwr.game.gui.view;

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
        this.fieldWidth = 40;
        this.fieldHeight = 40;
    }

    void paintTurtle(Graphics g, Turtle turtle) {
        Graphics2D g2d = (Graphics2D) g;
        System.out.println(turtle);
        switch (turtle) {
            case YELLOW:
                g2d.setColor(Color.YELLOW);
                g2d.fillOval(x, y, fieldWidth, fieldHeight);
                break;
            case BLUE:
                g2d.setColor(Color.BLUE);
                g2d.fillOval(x, y, fieldWidth, fieldHeight);
                break;

            case RED:
                g2d.setColor(Color.RED);
                g2d.fillOval(x, y, fieldWidth, fieldHeight);
                break;

            case GREEN:
                g2d.setColor(Color.GREEN);
                g2d.fillOval(x, y, fieldWidth, fieldHeight);
                break;

            case PURPLE:
                g2d.setColor(Color.MAGENTA);
                g2d.fillOval(x, y, fieldWidth, fieldHeight);
                break;

            default:
                throw new AssertionError("Unknown operations ");
        }

    }
}
