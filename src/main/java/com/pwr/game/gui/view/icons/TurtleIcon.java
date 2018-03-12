package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Turtle;

import javax.swing.*;
import java.awt.*;

public class TurtleIcon {

    private static final String TURTLE_ICON_PATH = "board-icons/turtle_";

    private int x;
    private int y;
    private StringBuilder path;

    public TurtleIcon(int x, int y) {
        this.x = x;
        this.y = y;
        path = new StringBuilder(TURTLE_ICON_PATH);
    }

    /**
     * Prints turtle on field
     * @param g
     * @param turtle
     */
    public void paintTurtleIcon(Graphics g, Turtle turtle) {
        path.append(turtle.toString().toLowerCase()).toString();
        path.append(".png").toString();

        Image image = new ImageIcon(getClass().getClassLoader().getResource(path.toString())).getImage();

        g.drawImage(image, x - image.getWidth(null) / 2, y - image.getHeight(null) / 2, null);
    }
}
