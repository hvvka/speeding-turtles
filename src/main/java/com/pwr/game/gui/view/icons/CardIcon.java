package com.pwr.game.gui.view.icons;

import com.pwr.game.engine.model.Card;

import javax.swing.*;
import java.awt.*;

public class CardIcon {

    private static final String CARD_ICON_PATH = "src/main/resources/cards-icons/";
    private static final int DEFAULT_ICON_WIDTH = 100;
    private static final int DEFAULT_ICON_HEIGHT = 160;

    private StringBuilder path;

    public CardIcon() {
        path = new StringBuilder(CARD_ICON_PATH);
    }

    public ImageIcon paintCard(Card card) {
        if (card.getMove() > 0) {
            path.append("plus_");
        } else {
            path.append("minus_");
        }

        if (Math.abs(card.getMove()) == 1) {
            path.append("one_");
        } else {
            path.append("two_");
        }

        path.append(card.getTurtle().toString().toLowerCase());
        path.append(".png");

        ImageIcon imageIcon = new ImageIcon(path.toString());
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT, Image.SCALE_SMOOTH);

        return new ImageIcon(newImage);
    }
}
