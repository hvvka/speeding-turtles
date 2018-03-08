package com.pwr.game.engine.model;

import java.util.List;

public final class Player {

    private final Integer id;

    private final Turtle turtle;

    private List<Card> cards;

    public Player(Integer id, Turtle turtle) {
        this.id = id;
        this.turtle = turtle;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getId() {
        return id;
    }
}
