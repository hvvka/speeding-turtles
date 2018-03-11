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
public class DecisionFrameController {

    private final DecisionFrame decisionFrame;
    private final Game game;

    private final String exitMessage = "Do zobaczenia przy następnej grze!";

    private JButton continueButton;
    private JButton rankingResetButton;
    private JButton gameExitButton;

    public DecisionFrameController(DecisionFrame decisionFrame, Game game) {
        this.decisionFrame = decisionFrame;
        this.game = game;

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
        decisionFrame.createCongratulationsLabel();
    }

    private void initListeners() {
        continueButton.addActionListener(al -> {
            game.newGame();
            decisionFrame.dispose();
        });

        rankingResetButton.addActionListener(al -> {
//            game.resetGame();
//            todo: reset
            decisionFrame.createRankingTable(game);
            decisionFrame.createCongratulationsLabel();
        });

        gameExitButton.addActionListener(al -> {
            JOptionPane.showMessageDialog(null, exitMessage);
            System.exit(0);
        });
    }

}
