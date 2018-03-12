package com.pwr.game.engine.model;

import java.util.List;

public class Board {

    private List<List<Turtle>> fields;

    public Board(List<List<Turtle>> fields) {
        this.fields = fields;
    }

    public List<List<Turtle>> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return fields != null ? fields.equals(board.fields) : board.fields == null;
    }

    @Override
    public int hashCode() {
        return fields != null ? fields.hashCode() : 0;
    }
}
