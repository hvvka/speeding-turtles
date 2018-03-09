package com.pwr.game.gui.controller;

import com.pwr.game.engine.model.Turtle;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private List<Turtle> turtlesOnField;

    public List<Turtle> createTurtles() {
        List turtles = new ArrayList<Turtle>();

        for (Turtle turtle : Turtle.values()) {

            turtles.add(turtle);
        }
        return turtles;

    }
}
