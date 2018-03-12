package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Player;
import com.pwr.game.gui.view.DecisionFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.Map;

/**
 * @author zlakomiak226190
 */
class DecisionFrameController {

    private static final String EXIT_MESSAGE = "Do zobaczenia przy następnej grze!";

    private final DecisionFrame decisionFrame;
    private final Game game;
    private final Player lastWinner;

    private JButton continueButton;
    private JButton rankingResetButton;
    private JButton gameExitButton;

    /**
     * Default DecisionFrameController, it takes
     * @param decisionFrame new DecisionFrame
     * @param game Game engine
     * @param lastWinner player which won last round
     * as parameters and set ups listeners and labels a for components on DecisionFrame
     */
    DecisionFrameController(DecisionFrame decisionFrame, Game game, Player lastWinner) {
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
            JOptionPane.showMessageDialog(null, EXIT_MESSAGE);
            System.exit(0);
        });
    }
}
