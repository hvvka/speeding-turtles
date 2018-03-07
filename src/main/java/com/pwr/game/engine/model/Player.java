package com.pwr.game.engine.model;

import java.util.List;

public class Player {

    private final Turtle turtle;

    private List<Card> cards;

    public Player(Turtle turtle) {
        this.turtle = turtle;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
