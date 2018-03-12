package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Turtle;

import javax.swing.*;
import java.awt.*;

public class TurtleIcon {
    private int x;
    private int y;
    private StringBuilder path;

    public TurtleIcon(int x, int y) {
        this.x = x;
        this.y = y;
        this.path =  new StringBuilder("src/main/resources/board-icons/turtle_");
    }

    public void paintTurtleIcon(Graphics g, Turtle turtle) {
        Graphics2D g2d = (Graphics2D) g;

        this.path.append(turtle.toString().toLowerCase()).toString();
        this.path.append(".png").toString();

        Image image = new ImageIcon(this.path.toString()).getImage();

        g.drawImage(image, x - image.getWidth(null) / 2, y - image.getHeight(null) / 2, null);
    }
}
