package com.pwr.game.gui.view;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {
    private JPanel panel1;


    public BoardFrame(BoardView boardView, ButtonPanel buttonPanel) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension.width, dimension.height);

        add(boardView, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
