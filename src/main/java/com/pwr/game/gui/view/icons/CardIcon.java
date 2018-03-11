package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Card;

import javax.swing.*;
import java.awt.*;

public class CardIcon {

    private StringBuilder path;

    public CardIcon(Card card){
        this.path = new StringBuilder("src/main/resources/cards-icons/");
    }

    public ImageIcon paintCard(Card card){

        if(card.getMove() > 0){
                this.path.append("plus_").toString();}
        else {
                this.path.append("minus_").toString();
        }

        if(Math.abs(card.getMove()) == 1){
            this.path.append("one_").toString();
        }
        else {
            this.path.append("two_").toString();
        }

        this.path.append(card.getTurtle().toString().toLowerCase()).toString();
        this.path.append(".png").toString();

        ImageIcon imageIcon = new ImageIcon(this.path.toString());
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(100, 160,  Image.SCALE_SMOOTH);

        return new ImageIcon(newimg);
        }

}
