package com.pwr.game.engine;

import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;

import java.util.Map;

public interface Game {

    Board newGame();

    Player newRound();

    Board makeMove(Card card);

    Map<Player, Integer> getResult();

    void winGame();

}
