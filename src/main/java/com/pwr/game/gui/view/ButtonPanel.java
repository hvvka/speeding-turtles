package com.pwr.game.gui.view;

import com.pwr.game.engine.model.Player;
import com.pwr.game.gui.view.icons.CardIcon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ButtonPanel extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private CardIcon cardIcon;
    private ArrayList<JButton> buttonList;

    public ButtonPanel(Player player){
        setButtonList();
        setButtonImages(player);
    }

    private void setButtonList(){

        buttonList = new ArrayList<JButton>();

        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
    }

    private void setButtonImages(Player player){

        for(int i = 0; i < player.getCards().size(); i++) { ;
            cardIcon = new CardIcon(player.getCards().get(i));
            buttonList.get(i).setIcon(cardIcon.paintCard(player.getCards().get(i)));

        }
    }
}
