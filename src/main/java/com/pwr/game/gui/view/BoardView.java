package com.pwr.game.gui.view;

import com.pwr.game.engine.Game;
import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.MainFrame;
import com.pwr.game.gui.view.icons.FieldIcon;
import com.pwr.game.gui.view.icons.TurtleIcon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends JPanel {

    //współrzędne pierwszego pola na planszy
    //zmiana spowoduje przesunięcie całej planszy (pól i zółwi)
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int xStart = (int) (dimension.width/1.7);
    private int yStart = (int) dimension.height/2;
    private FieldIcon field;

    private List<List<Turtle>> fields;
    private static List<FieldIcon> fieldsIcons;


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

        int xTranslation = (int) (dimension.width/7.4);
        int yTranslation = (int) (dimension.height/18);;
        int x = xStart;
        int y = yStart;

        fieldsIcons = new ArrayList<FieldIcon>();
        field = new FieldIcon(x, y);
        fieldsIcons.add(field);
        field.paintField(g, true);

        for (int i = 0; i < GameImpl.FIELDS_NUMBER; i++) {
            x -= xTranslation;
            y -= yTranslation;

            field = new FieldIcon(x, y);
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

                int x = fieldsIcons.get(i).getX() - 70;
                int y = fieldsIcons.get(i).getY();

                for (int j = 0; j < fields.get(i).size(); j++) {
                    TurtleIcon turtleIcon = new TurtleIcon(x, y);
                    turtleIcon.paintTurtle(g, fields.get(i).get(j));
                    x += 35;
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
        setFocusable(true);


    }

//    public static void main(String[] args) {
//
//        MainFrame mf = new MainFrame();
//
//    }
}
