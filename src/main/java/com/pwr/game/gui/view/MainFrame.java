package com.pwr.game.gui.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
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
    private JTextField playerOneNameTextField;
    private JTextField playerTwoNameTextField;
    private JTextField playerThreeNameTextField;
    private JTextField playerFourNameTextField;
    private JTextField playerFiveNameTextField;
    private JLabel playerOneNameLabel;
    private JLabel playerTwoNameLabel;
    private JLabel playerThreeNameLabel;
    private JLabel playerFourNameLabel;
    private JLabel playerFiveNameLabel;
    private JLabel logoLabel;

    public MainFrame() {
        super("Speeding–Turtles");
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        logoLabel.setIcon(new ImageIcon(new ImageIcon("resources/applogo.png")
                .getImage()
                .getScaledInstance(490, 245, Image.SCALE_SMOOTH)));
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JTextField getPlayerOneNameTextField() {
        return playerOneNameTextField;
    }

    public JTextField getPlayerTwoNameTextField() {
        return playerTwoNameTextField;
    }

    public JTextField getPlayerThreeNameTextField() {
        return playerThreeNameTextField;
    }

    public JTextField getPlayerFourNameTextField() {
        return playerFourNameTextField;
    }

    public JTextField getPlayerFiveNameTextField() {
        return playerFiveNameTextField;
    }

}
