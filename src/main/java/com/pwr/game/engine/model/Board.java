package com.pwr.game.engine.model;

import java.util.List;

public class Board {

    private List<List<Turtle>> fields;

    public List<List<Turtle>> getFields() {
        return fields;
    }

    public void setFields(List<List<Turtle>> fields) {
        this.fields = fields;
    }

    public Board(List<List<Turtle>> fields) {
        this.fields = fields;
    }

}
