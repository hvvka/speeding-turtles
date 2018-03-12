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

    private BoardView boardView;
    private ButtonPanel buttonPanel;
    private Player player;
    private Game game;
    private BoardFrame boardFrame;

    BoardController(Board board, Game game) {
        this.player = game.newRound();
        this.boardView = new BoardView(board.getFields());
        this.buttonPanel = new ButtonPanel(player);
        this.game = game;
        this.boardFrame = new BoardFrame(boardView, buttonPanel);

        showNextPlayer();
        initListeners(game);
    }

    private void initListeners(Game game) {
        ActionListener buttonsListener = actionEvent -> {

            JButton button = (JButton) actionEvent.getSource();
            Card card = player.getCards().get(Integer.parseInt(button.getName()));

            List<List<Turtle>> fields = game.makeMove(card).getFields();
            winnerCheck(fields.get(fields.size() - 1));
            boardView.setFields(fields);
            player = game.newRound();
            buttonPanel.setButtonImages(player);

            showNextPlayer();

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

    private void winnerCheck(List<Turtle> lastField) {
        if (lastField.isEmpty()) return;
        player = game.winGame();
        new DecisionFrameController(new DecisionFrame(), game, player);
        boardFrame.dispose();
    }
}



