package com.pwr.game.gui.controller;

import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.view.BoardView;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private Board board;
    private BoardView boardView;
    private List<List<Turtle>> fields;
    static int FIELDS_NUMBER = 8;


    public BoardController(BoardView boardView, Board board, GameImpl game){

        fields = board.getFields();
        //game.makeMove()
       // boardView = new BoardView(fields);
    }

    public List<List<Turtle>> getFields() {
        return fields;
    }

 //   przykladowe dodane dane aby zobaczyc czy dobrze sie wyswietla
//    private List<List<Turtle>> createFields() {
//        List<List<Turtle>> fields = new ArrayList<>(FIELDS_NUMBER);
//        fields.add(createTurtles());
//        fields.add(createTurtles());
//        return fields;
//    }
//
//
//
//    public List<Turtle> createTurtles() {
//        List turtles = new ArrayList<Turtle>();
//
//        for (Turtle turtle : Turtle.values()) {
//
//            turtles.add(turtle);
//        }
//        return turtles;
//
//    }
}


