package com.pwr.game.gui.view;

import com.pwr.game.engine.model.Player;
import com.pwr.game.gui.view.icons.CardIcon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class ButtonPanel extends JPanel {
    private JButton card1Button;
    private JButton card2Button;
    private JButton card3Button;
    private JButton card4Button;
    private JButton card5Button;
    private JLabel currentPlayerLabel;
    private CardIcon cardIcon;
    private ButtonGroup buttons;
    private ArrayList<AbstractButton> listButtons;

    public JButton getCard1Button() {
        return card1Button;
    }

    public JButton getCard2Button() {
        return card2Button;
    }

    public JButton getCard3Button() {
        return card3Button;
    }

    public JButton getCard4Button() {
        return card4Button;
    }

    public JButton getCard5Button() {
        return card5Button;
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

        buttons.add(card1Button);
        buttons.add(card2Button);
        buttons.add(card3Button);
        buttons.add(card4Button);
        buttons.add(card5Button);

        add(currentPlayerLabel);
        add(card1Button);
        card1Button.setName("0");
        add(card2Button);
        card2Button.setName("1");
        add(card3Button);
        card3Button.setName("2");
        add(card4Button);
        card4Button.setName("3");
        add(card5Button);
        card5Button.setName("4");

        listButtons = Collections.list(buttons.getElements());

    }

    public void setButtonImages(Player player){

        for(int i = 0; i < player.getCards().size(); i++) {
            cardIcon = new CardIcon(player.getCards().get(i));
            listButtons.get(i).setIcon(cardIcon.paintCard(player.getCards().get(i)));
        }
        currentPlayerLabel.setText("Teraz gra " + player.getName() +":" );
    }
}
