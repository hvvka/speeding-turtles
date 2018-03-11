package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.model.Player;
import com.pwr.game.gui.view.DecisionFrame;

import javax.swing.*;

/**
 * @author zlakomiak226190
 */
public class DecisionFrameController {

    private final DecisionFrame decisionFrame;
    private final Game game;
    private final Player lastWinner;

    private final String exitMessage = "Do zobaczenia przy następnej grze!";

    private JButton continueButton;
    private JButton rankingResetButton;
    private JButton gameExitButton;

    public DecisionFrameController(DecisionFrame decisionFrame, Game game, Player lastWinner) {
        this.decisionFrame = decisionFrame;
        this.game = game;
        this.lastWinner = lastWinner;

        initComponents();
        initListeners();
    }

    private void initComponents() {
        decisionFrame.setVisible(true);

        continueButton = decisionFrame.getContinueButton();
        rankingResetButton = decisionFrame.getRankingResetButton();
        gameExitButton = decisionFrame.getGameExitButton();

        decisionFrame.createButtonsLabels();
        decisionFrame.createRankingTable(game);
        decisionFrame.createCongratulationsLabel(lastWinner);
    }

    private void initListeners() {
        continueButton.addActionListener(al -> {
            new BoardController(game.newGame(), game);
            decisionFrame.dispose();
        });

        rankingResetButton.addActionListener(al -> {
            new MainFrameController();
            decisionFrame.dispose();
        });

        gameExitButton.addActionListener(al -> {
            JOptionPane.showMessageDialog(null, exitMessage);
            System.exit(0);
        });
    }
}
