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

    public String getName() {
        return name;
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (turtle != player.turtle) return false;
        return cards != null ? cards.equals(player.cards) : player.cards == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (turtle != null ? turtle.hashCode() : 0);
        result = 31 * result + (cards != null ? cards.hashCode() : 0);
        return result;
    }
}
