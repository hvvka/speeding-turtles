package com.pwr.game.engine;

import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;

public interface Game {

    void newGame();

    void resetGame();

    Player newRound();

    Board makeMove(Player player, Card card);

}
