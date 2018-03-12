package com.pwr.game.gui.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;

    private final URL LOGO_PATH = getClass().getClassLoader().getResource("applogo.png");
    private static final int LOGO_WIDTH = 490;
    private static final int LOGO_HEIGHT = 245;

    private JPanel mainPanel;
    private JButton startButton;
    private JLabel logoLabel;

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

    public MainFrame() {
        super("SpeedingTurtles");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println(LOGO_PATH);
        logoLabel.setIcon(new ImageIcon(new ImageIcon(LOGO_PATH)
                .getImage()
                .getScaledInstance(LOGO_WIDTH, LOGO_HEIGHT, Image.SCALE_SMOOTH)));
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
