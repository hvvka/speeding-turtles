package com.pwr.game.engine.model;

import java.util.List;

public final class Player {

    private final Integer id;

    private final String name;

    private final Turtle turtle;

    private List<Card> cards;

    public Player(Integer id, String name, Turtle turtle) {
        this.id = id;
        this.name = name;
        this.turtle = turtle;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getId() {
        return id;
    }
}
