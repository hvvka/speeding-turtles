package com.pwr.game.gui.controller;

import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.view.BoardView;
import com.pwr.game.gui.view.ButtonPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private Board board;
    private BoardView boardView;
    private List<List<Turtle>> fields;
    static int FIELDS_NUMBER = 8;


    public BoardController(BoardView boardView, ButtonPanel buttonPanel, Board board, GameImpl game){
        fields = board.getFields();

    }

    public List<List<Turtle>> getFields() {
        return fields;
    }
}


