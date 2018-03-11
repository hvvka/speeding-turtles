package com.pwr.game.gui.controller;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Board;
import com.pwr.game.gui.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrameController {

    private MainFrame mainFrame;
    private JTextField playerOneTextField;
    private JTextField playerTwoTextField;
    private JTextField playerThreeTextField;
    private JTextField playerFourTextField;
    private JTextField playerFiveTextField;
    private JButton startButton;

    public MainFrameController() {
        initComponents();
        initListeners();
    }

    private void initListeners() {
        startButton.addActionListener( ae -> {
            Game game = new GameImpl(getAllPlayerNames());
            Board board = game.newGame();
            //TODO create boardController, think what references it should keep
        });
    }

    private List<String> getAllPlayerNames(){
        List<String> playerNames = new ArrayList<>();
        playerNames.add(playerOneTextField.getText());
        playerNames.add(playerTwoTextField.getText());
        playerNames.add(playerThreeTextField.getText());
        playerNames.add(playerFourTextField.getText());
        playerNames.add(playerFiveTextField.getText());
        return playerNames;
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        startButton = mainFrame.getStartButton();
        playerOneTextField = mainFrame.getTextFieldPlayerOneName();
        playerTwoTextField = mainFrame.getTextFieldPlayerTwoName();
        playerThreeTextField = mainFrame.getTextFieldPlayerThreeName();
        playerFourTextField = mainFrame.getTextFieldPlayerFourName();
        playerFiveTextField = mainFrame.getTextFieldPlayerFiveName();
    }
}
