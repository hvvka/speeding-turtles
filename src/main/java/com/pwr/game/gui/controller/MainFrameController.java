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
        String playerOneName   = playerOneTextField.getText();
        String playerTwoName   = playerTwoTextField.getText();
        String playerThreeName = playerThreeTextField.getText();
        String playerFourName  = playerFourTextField.getText();
        String playerFiveName  = playerFiveTextField.getText();
        if(playerOneName.equals(""))   playerOneName   = "Fluttershy";
        if(playerTwoName.equals(""))   playerTwoName   = "Rainbow Dash";
        if(playerThreeName.equals("")) playerThreeName = "Big Macintosh";
        if(playerFourName.equals(""))  playerFourName  = "Green Jewel";
        if(playerFiveName.equals(""))  playerFiveName  = "Twilight Sparkle";
        playerNames.add(playerOneName);
        playerNames.add(playerTwoName);
        playerNames.add(playerThreeName);
        playerNames.add(playerFourName);
        playerNames.add(playerFiveName);
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
