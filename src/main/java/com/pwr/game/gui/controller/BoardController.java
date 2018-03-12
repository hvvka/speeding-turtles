package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.view.BoardFrame;
import com.pwr.game.gui.view.BoardView;
import com.pwr.game.gui.view.ButtonPanel;
import com.pwr.game.gui.view.DecisionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

class BoardController {

    private static final String NEXT_PLAYER_MESSAGE = "Kolejnym graczem jest ";
    private static final String WINNER_MESSAGE1 = "Zwycięzcą jest ";
    private static final String WINNER_MESSAGE2 = "\nGratulujemy!";

    private BoardView boardView;
    private ButtonPanel buttonPanel;
    private Player player;
    private Game game;
    private BoardFrame boardFrame;

    /**
     * Default constuctor of BoardController it takes
     * @param board Board returned by game engine
     * @param game Game engine
     * as parameters, and creates BoardFrame, and sets up  listeners for all components
     */
    BoardController(Board board, Game game) {
        this.player = game.newRound();
        this.boardView = new BoardView(board.getFields());
        this.buttonPanel = new ButtonPanel(player);
        this.game = game;
        this.boardFrame = new BoardFrame(boardView, buttonPanel);

        showNextPlayer();
        initListeners(game);
    }

    /**
     * Initializes listeners for BoardFrame buttons
     * @param game takes Game engine as parameter to make move based on card used
     */
    private void initListeners(Game game) {
        ActionListener buttonsListener = actionEvent -> {

            JButton button = (JButton) actionEvent.getSource();
            Card card = player.getCards().get(Integer.parseInt(button.getName()));

            List<List<Turtle>> fields = game.makeMove(card).getFields();
            boardView.setFields(fields);
            player = game.newRound();
            buttonPanel.setButtonImages(player);

            if(!winnerCheck(fields.get(fields.size() - 1))){
                showNextPlayer();}

            boardView.repaint();
            buttonPanel.repaint();
        };

        buttonPanel.getCard1Button().addActionListener(buttonsListener);
        buttonPanel.getCard2Button().addActionListener(buttonsListener);
        buttonPanel.getCard3Button().addActionListener(buttonsListener);
        buttonPanel.getCard4Button().addActionListener(buttonsListener);
        buttonPanel.getCard5Button().addActionListener(buttonsListener);
    }

    private void showNextPlayer() {
        buttonPanel.setButtonsInvisible(false);
        JOptionPane.showMessageDialog(new Frame(), NEXT_PLAYER_MESSAGE + player.getName());
        buttonPanel.setButtonsInvisible(true);
    }

    private boolean winnerCheck(List<Turtle> lastField) {
        if (lastField.isEmpty()) return false;
        player = game.winGame();
        JOptionPane.showMessageDialog(new Frame(), WINNER_MESSAGE1 + player.getName() + WINNER_MESSAGE2);
        new DecisionFrameController(new DecisionFrame(), game, player);
        boardFrame.dispose();
        return true;
    }
}



