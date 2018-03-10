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
  //  private BoardController boardController;
    private List<Turtle> turtles;
    private List<List<Turtle>> fields;

    private static List<Field> fieldsIcons;


    //metoda wywoływana jak trzeba przerysować ekran
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //zawsze maluje całą planszę
        paintBoardFields(g);

        //maluje żówie na odpowiednich polach
        paintTurtlesOnFields(g, fields);

    }


    public void paintBoardFields(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int xTranslation = 200;
        int yTranslation = 70;
        int x = xStart;
        int y = yStart;

        fieldsIcons = new ArrayList<Field>();
        field = new Field(x, y);
        fieldsIcons.add(field);
        field.paintField(g, true);

        for (int i = 0; i < 8; i++) {
            x -= xTranslation;
            y -= yTranslation;

            field = new Field(x, y);
            field.paintField(g, false);
            fieldsIcons.add(field);

            xTranslation = -xTranslation;
        }
    }

    //rysuje żółwie odpowiednio na polach
    public void paintTurtlesOnFields(Graphics g, List<List<Turtle>> fields) {
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < fields.size(); i++) {
            if (i == 0) {

                int x = fieldsIcons.get(i).getX() - 60;
                int y = fieldsIcons.get(i).getY();

                for (int j = 0; j < fields.get(i).size(); j++) {
                    TurtleIcon turtleIcon = new TurtleIcon(x, y);
                    turtleIcon.paintTurtle(g, fields.get(i).get(j));
                    x += 30;
                }
            } else {
                int x = fieldsIcons.get(i).getX();
                int y = fieldsIcons.get(i).getY();

                for (int j = 0; j < fields.get(i).size(); j++) {
                    TurtleIcon turtleIcon = new TurtleIcon(x, y);
                    turtleIcon.paintTurtle(g, fields.get(i).get(j));
                    y -= 10;
                }
            }
        }
    }

    public BoardView(List<List<Turtle>> fields) {
        this.fields = fields;
//        setFocusable(true);
//        setLayout(new FlowLayout());
//        setContentPane(this);
//        setVisible(true);
        //this.boardController = new BoardController();


    }

    public static void main(String[] args) {
//        żeby dodać ten panel do frame'u rekomendowane jest
//        dodanie go w następujący sposób (bo inaczej nie bedzie śmigać rysowanie):
//        BoardView board = new BoardView();
//        board.setFocusable(true);
//        board.setLayout(new FlowLayout());
//        setContentPane(board);
//        setVisible(true);


        MainFrame mf = new MainFrame();

    }
}
