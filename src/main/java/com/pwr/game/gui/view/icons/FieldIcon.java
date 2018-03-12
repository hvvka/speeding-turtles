package com.pwr.game.gui.view.icons;

import javax.swing.*;
import java.awt.*;

public class FieldIcon {
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
        Graphics2D g2d = (Graphics2D) g;
        Image image;

        if(!last){
            image = new ImageIcon("src/main/resources/board-icons/field.png").getImage();}
        else{
            image = new ImageIcon("src/main/resources/board-icons/field_finish.png").getImage();}


        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
    }
}

