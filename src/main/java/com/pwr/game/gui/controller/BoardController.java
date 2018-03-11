package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.MainFrame;
import com.pwr.game.gui.view.BoardView;
import com.pwr.game.gui.view.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardController {

    private List<List<Turtle>> fields;
    private BoardView boardView;
    private ButtonPanel buttonPanel;
    private Player player;
    public BoardController(Board board, GameImpl game){

        this.player = game.newRound();
        this.boardView = new BoardView(board.getFields());
        this.buttonPanel = new ButtonPanel(player);
        this.fields = board.getFields();

        MainFrame mf = new MainFrame(boardView, buttonPanel);

        initListeners(game);
        }

    public BoardView getBoardView() {
        return boardView;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    private void initListeners(Game game){
        ActionListener buttonsListener = actionEvent -> {
            JButton button = (JButton) actionEvent.getSource();
            Card card = player.getCards().get(Integer.parseInt(button.getName()));
            System.out.println(card.getMove() + card.getTurtle().toString());
            boardView.setFields(game.makeMove(card).getFields());
            boardView.repaint();
            buttonPanel.setButtonImages(player = game.newRound());
            buttonPanel.repaint();
        };
        buttonPanel.getButton1().addActionListener(buttonsListener);
        buttonPanel.getButton2().addActionListener(buttonsListener);
        buttonPanel.getButton3().addActionListener(buttonsListener);
        buttonPanel.getButton4().addActionListener(buttonsListener);
        buttonPanel.getButton5().addActionListener(buttonsListener);
    }

    public static void main(String[] args) {
        GameImpl game = new GameImpl(Arrays.asList("Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"));
        BoardController boardController = new BoardController(game.newGame(), game);
    }
}



