package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.GameImpl;
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
import java.util.Arrays;
import java.util.List;

public class BoardController {

    private List<List<Turtle>> fields;
    private BoardView boardView;
    private ButtonPanel buttonPanel;
    private Player player;
    private Board board;
    private Game game;
    private BoardFrame boardFrame;

    public BoardController(Board board, Game game) {

        this.player = game.newRound();
        this.boardView = new BoardView(board.getFields());
        this.buttonPanel = new ButtonPanel(player);
        this.board = board;
        this.game = game;
        this.boardFrame = new BoardFrame(boardView, buttonPanel);

        showNextPlayer();

        initListeners(game);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    private void initListeners(Game game) {
        ActionListener buttonsListener = actionEvent -> {

            JButton button = (JButton) actionEvent.getSource();
            Card card = player.getCards().get(Integer.parseInt(button.getName()));

            List<List<Turtle>> fields = game.makeMove(card).getFields();
            winnerCheck(fields.get(fields.size() - 1));
            boardView.setFields(fields);
            buttonPanel.setButtonImages(player = game.newRound());

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
        JOptionPane.showMessageDialog(new Frame(), "Kolejnym graczem jest " + player.getName());
        buttonPanel.setButtonsInvisible(true);

    }

    private void winnerCheck(List<Turtle> lastField) {
        if (lastField.size() == 0) return;
        //TODO: would be nice if winGame() would return a player which i could inject into new DecisionFrameController instance
        game.winGame();
        //TODO: change given parameter from current player to winning one
        new DecisionFrameController(new DecisionFrame(), game, player);
        boardFrame.dispose();

    }

    public static void main(String[] args) {
        GameImpl game = new GameImpl(Arrays.asList("Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"));
        BoardController boardController = new BoardController(game.newGame(), game);
    }
}



