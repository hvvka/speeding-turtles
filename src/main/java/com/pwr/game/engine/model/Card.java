package com.pwr.game.engine.model;

public final class Card {

    private final Turtle turtle;

    private final int move;

    public Card(Turtle turtle, int move) {
        this.turtle = turtle;
        this.move = move;
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public int getMove() {
        return move;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (move != card.move) return false;
        return turtle == card.turtle;
    }

    @Override
    public int hashCode() {
        int result = turtle != null ? turtle.hashCode() : 0;
        result = 31 * result + move;
        return result;
    }
}
