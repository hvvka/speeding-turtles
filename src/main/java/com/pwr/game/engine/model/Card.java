package com.pwr.game.engine.model;

public class Card {

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
}
