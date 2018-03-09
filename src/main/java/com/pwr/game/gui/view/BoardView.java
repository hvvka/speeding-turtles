package com.pwr.game.gui.view;

import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.MainFrame;
import com.pwr.game.gui.controller.BoardController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends JPanel {
    //współrzędne pierwszego pola na planszy
    //zmiana spowoduje przesunięcie całej planszy (pól i zółwi)
    private int xStart = 680;
    private int yStart = 630;

    private Field field;
    private BoardController boardController;
    private List<Turtle> turtles;

    private List<Field> fields;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoardFields(g);
        paintTurtlesOnStart(g, turtles);
    }


    void paintBoardFields(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        int xTranslation = 200;
        int yTranslation = 70;
        int x = xStart;
        int y = yStart;

        fields = new ArrayList<Field>();
        field = new Field(x,y);
        fields.add(field);
        field.paintField(g, true);

        for(int i = 0; i < 8; i++){
            x -= xTranslation;
            y -= yTranslation;

            field = new Field(x,y);
            field.paintField(g, false);
            fields.add(field);

            xTranslation = -xTranslation; }
    }


    void paintTurtlesOnStart(Graphics g, List<Turtle> turtles){
        Graphics2D g2d = (Graphics2D) g;

        int xTranslation = 2;
        int yTranslation = 5;
        int x = xStart + xTranslation;
        int y = yStart + yTranslation;

        for(Turtle turtle :  turtles){
            TurtleIcon turtleIcon = new TurtleIcon(x, y);
            turtleIcon.paintTurtle(g, turtle);

            x += 34;
        }
    }



    void paintTurtlesOnField(Graphics g, List<Turtle> turtles){}

    public BoardView(){
        this.boardController = new BoardController();
        this.turtles = boardController.createTurtles();

    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();

    }
}
