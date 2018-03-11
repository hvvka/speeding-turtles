package com.pwr.game.gui.view;

import com.pwr.game.engine.model.Player;
import com.pwr.game.gui.view.icons.CardIcon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class ButtonPanel extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private CardIcon cardIcon;
    private ButtonGroup buttons;
    private ArrayList<AbstractButton> listButtons;

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public JButton getButton5() {
        return button5;
    }

    public ArrayList<AbstractButton> getListButtons() {
        return listButtons;
    }

    public ButtonPanel(Player player){
        setButtonList();
        setButtonImages(player);
    }


    private void setButtonList(){

        buttons = new ButtonGroup();

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);

        add(button1);
        button1.setName("0");
        add(button2);
        button2.setName("1");
        add(button3);
        button3.setName("2");
        add(button4);
        button4.setName("3");
        add(button5);
        button5.setName("4");

        listButtons = Collections.list(buttons.getElements());

    }

    public void setButtonImages(Player player){

        for(int i = 0; i < player.getCards().size(); i++) {
            cardIcon = new CardIcon(player.getCards().get(i));
            listButtons.get(i).setIcon(cardIcon.paintCard(player.getCards().get(i)));
        }
    }
}
