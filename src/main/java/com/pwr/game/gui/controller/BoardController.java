package com.pwr.game.gui.controller;

import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.view.BoardView;
import com.pwr.game.gui.view.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private List<List<Turtle>> fields;


    public BoardController(BoardView boardView, ButtonPanel buttonPanel, Board board, GameImpl game){
        fields = board.getFields();

        };
    }



