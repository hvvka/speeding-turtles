package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
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
    private BoardView boardView;
    private ButtonPanel buttonPanel;


    public BoardController(BoardView boardView, ButtonPanel buttonPanel, Board board, GameImpl game, Player player){
        this.boardView = boardView;
        this.buttonPanel = buttonPanel;
        this.fields = board.getFields();
        initListeners(player, game);
        }

    private void initListeners(Player player, Game game){
        ActionListener buttonsListener = actionEvent -> {
            JButton button = (JButton) actionEvent.getSource();
            Card card = player.getCards().get(Integer.parseInt(button.getName()));
            game.makeMove(card);
            boardView.repaint();
            buttonPanel.setButtonImages(game.newRound());
        };
        buttonPanel.getButton1().addActionListener(buttonsListener);
        buttonPanel.getButton2().addActionListener(buttonsListener);
        buttonPanel.getButton3().addActionListener(buttonsListener);
        buttonPanel.getButton4().addActionListener(buttonsListener);
        buttonPanel.getButton5().addActionListener(buttonsListener);
    }
    }



