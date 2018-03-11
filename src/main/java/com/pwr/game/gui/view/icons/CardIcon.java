package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Card;

import javax.swing.*;

public class CardIcon {

    private String path;

    public CardIcon(Card card){
        this.path = "resources/";
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

        return new ImageIcon(TurtleIcon.class.getResource(this.path));

        }
}
