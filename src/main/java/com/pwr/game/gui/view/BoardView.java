package com.pwr.game.gui.view;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private int fieldWidth = 180;
    private int fieldHeight = 50;
    private int x = 680;
    private int y = 630;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoardFields(g);
    }


    void paintBoardFields(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int xTranslation = 200;
        int yTranslation = 70;

        for(int i = 0; i < 8; i++){
            x -= xTranslation;
            y -= yTranslation;
             g2d.drawOval(x,y, fieldWidth,fieldHeight);
             xTranslation = -xTranslation; }
    }

    public BoardView(){
    }

//    public static void main(String[] args) {
//        MainFrame mf = new MainFrame();
//
//
//    }
}
