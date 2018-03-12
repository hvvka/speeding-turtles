package com.pwr.game.gui.view;


import com.pwr.game.engine.GameImpl;
import com.pwr.game.engine.model.Turtle;
import com.pwr.game.gui.view.icons.FieldIcon;
import com.pwr.game.gui.view.icons.TurtleIcon;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends JPanel {

    private final URL BACKROUND_PATH = getClass().getClassLoader().getResource("board-icons/background.png");

    //zmiana spowoduje przesunięcie całej planszy (pól i zółwi)
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int xStart = (int) (dimension.width / 7.5);
    private int yStart = (int) (dimension.height / 1.6);
    private transient Image imageIcon;

    private List<List<Turtle>> fields;
    private transient List<FieldIcon> fieldsIcons;

    public void setFields(List<List<Turtle>> fields) {
        this.fields = fields;
    }

    public BoardView(List<List<Turtle>> fields) {
        this.fields = fields;
        imageIcon = new ImageIcon(BACKROUND_PATH).getImage();
        setFocusable(true);
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


    private void paintBoardFields(Graphics g) {
        int xTranslation = dimension.width / 9;
        int yTranslation = dimension.height / 16;

        int x = xStart;
        int y = yStart;

        fieldsIcons = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {
            FieldIcon field = new FieldIcon(x, y);
            field.paintFieldIcon(g, false);
            fieldsIcons.add(field);

            x += xTranslation;
            y -= yTranslation;

            if (i == fields.size() - 1) {
                field.paintFieldIcon(g, true);
                fieldsIcons.add(field);
            }
        }
    }
    //rysuje żółwie odpowiednio na polach

    private void paintTurtlesOnFields(Graphics g, List<List<Turtle>> fields) {
        for (int i = 0; i < GameImpl.FIELDS_NUMBER; i++) {
            if (i == 0) {
                int x = fieldsIcons.get(i).getX() - 30;
                int y = fieldsIcons.get(i).getY() - 40;

                for (int j = 0; j < fields.get(i).size(); j++) {
                    TurtleIcon turtleIcon = new TurtleIcon(x, y);
                    turtleIcon.paintTurtleIcon(g, fields.get(i).get(j));
                    if (j != 2) {
                        x += 35;
                        y += 15;
                    } else {
                        x = fieldsIcons.get(i).getX() - 30;
                    }
                }
            } else {
                int x = fieldsIcons.get(i).getX();
                int y = fieldsIcons.get(i).getY();

                for (int j = 0; j < fields.get(i).size(); j++) {
                    TurtleIcon turtleIcon = new TurtleIcon(x, y);

                    turtleIcon.paintTurtleIcon(g, fields.get(i).get(j));
                    y -= 15;
                }
            }
        }
    }
}

