package com.pwr.game.gui.view;

import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
import com.pwr.game.gui.view.icons.CardIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Card card;

    public ButtonPanel(Player player){
        setButtonList();
        setButtonImages(player);

        ActionListener buttonsListener = actionEvent -> {
            JButton button = (JButton) actionEvent.getSource();
            card = player.getCards().get(Integer.parseInt(button.getName()));
        };

        button1.addActionListener(buttonsListener);
        button2.addActionListener(buttonsListener);
        button3.addActionListener(buttonsListener);
        button4.addActionListener(buttonsListener);
        button5.addActionListener(buttonsListener);

    }

    public Card getCard() {
        return card;
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

    private void setButtonImages(Player player){

        for(int i = 0; i < player.getCards().size(); i++) {
            System.out.println(player.getCards().size());
            cardIcon = new CardIcon(player.getCards().get(i));
            listButtons.get(i).setIcon(cardIcon.paintCard(player.getCards().get(i)));
        }
    }
}
