package com.pwr.game.gui.view;

import javax.swing.*;

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
    private JButton buttonStart;
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

    public MainFrame() {
        super("Speeding–Turtles");
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
