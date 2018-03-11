package com.pwr.game.gui;

import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.controller.BoardController;
import com.pwr.game.gui.view.BoardView;
import com.pwr.game.gui.view.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFrame extends JFrame {
    private ButtonPanel buttonPanel;
    private BoardView boardView;


    public MainFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension.width, dimension.height);

        GameImpl game = new GameImpl(Arrays.asList("Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"));
        Board board = game.newGame();
        Player player = game.newRound();
        boardView = new BoardView(board.getFields());
        buttonPanel = new ButtonPanel(player);

        BoardController boardController = new BoardController(boardView, buttonPanel, board, game, player);

        add(buttonPanel, BorderLayout.SOUTH);
        add(boardView, BorderLayout.CENTER);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
