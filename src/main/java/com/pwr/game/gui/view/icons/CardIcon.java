package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Card;

import javax.swing.*;
import java.awt.*;

public class CardIcon {

    private String path;

    public CardIcon(Card card){
        this.path = "src/main/resources/turtle-icons/";
    }

    public ImageIcon paintCard(Card card){

        if(card.getMove() > 0){
                this.path += "plus_";}
        else {
                this.path += "minus_";
        }

        if(Math.abs(card.getMove()) == 1){
            this.path += "one_";
        }
        else {
            this.path += "two_";
        }

        this.path += card.getTurtle().toString().toLowerCase() + ".png";

//        ImageIcon imageIcon = new ImageIcon(TurtleIcon.class.getResource(this.path));
        ImageIcon imageIcon = new ImageIcon(this.path);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(100, 160,  java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(newimg);
        }

}
