package com.pwr.game.gui.view;

import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Turtle;
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
    private int xStart = (int) (dimension.width/7.5);
    private int yStart = (int) (dimension.height/1.6);
    private FieldIcon field;
    private Image imageIcon;

    private List<List<Turtle>> fields;
    private List<FieldIcon> fieldsIcons;

    public void setFields(List<List<Turtle>> fields) {
        this.fields = fields;
    }

    //metoda wywoływana jak trzeba przerysować ekran
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imageIcon, 0, 0, getWidth(), getHeight(), this);
        //zawsze maluje całą planszę
        paintBoardFields(g);

        //maluje żówie na odpowiednich polach
        paintTurtlesOnFields(g, fields);
    }


    public void paintBoardFields(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int xTranslation = (int) (dimension.width/9);
        int yTranslation = (int) (dimension.height/16);;
        int x = xStart;
        int y = yStart;

        fieldsIcons = new ArrayList<FieldIcon>();
        field = new FieldIcon(x, y);
        fieldsIcons.add(field);
        field.paintFieldIcon(g, true);

        for (int i = 0; i < fields.size()-1; i++) {
            x += xTranslation;
            y -= yTranslation;

            field = new FieldIcon(x, y);
            field.paintFieldIcon(g, false);
            fieldsIcons.add(field);

//            if(i%3 == 1){
//                xTranslation = -xTranslation;}

        }
    }

    //rysuje żółwie odpowiednio na polach
    public void paintTurtlesOnFields(Graphics g, List<List<Turtle>> fields) {
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < GameImpl.FIELDS_NUMBER; i++) {
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
        imageIcon = new ImageIcon("src/main/resources/board-icons/background.jpg").getImage();
        setFocusable(true);
    }

}
