package com.pwr.game.gui.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    /**
     * Default frame's width.
     */
    private static final int WIDTH = 500;
    /**
     * Default frame's height.
     */
    private static final int HEIGHT = 500;
    private JPanel mainPanel;
    private JButton startButton;
    private JLabel labelPlayerOneName;
    private JTextField textFieldPlayerFiveName;
    private JTextField textFieldPlayerOneName;
    private JTextField textFieldPlayerTwoName;
    private JTextField textFieldPlayerThreeName;
    private JTextField textFieldPlayerFourName;
    private JLabel labelPlayerTwoName;
    private JLabel labelPlayerThreeName;
    private JLabel labelPlayerFourName;
    private JLabel labelPlayerFiveName;
    private JLabel labelLogo;

    public MainFrame() {
        super("Speeding–Turtles");
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JTextField getTextFieldPlayerFiveName() {
        return textFieldPlayerFiveName;
    }

    public JTextField getTextFieldPlayerOneName() {
        return textFieldPlayerOneName;
    }

    public JTextField getTextFieldPlayerTwoName() {
        return textFieldPlayerTwoName;
    }

    public JTextField getTextFieldPlayerThreeName() {
        return textFieldPlayerThreeName;
    }

    public JTextField getTextFieldPlayerFourName() {
        return textFieldPlayerFourName;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
