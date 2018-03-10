package com.pwr.game.engine;

import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;

import java.util.List;
import java.util.Map;

public interface Game {

    void newGame();

    void resetGame();

    Player newRound();

    Board makeMove(Player player, Card card);

    List<Player> getPlayers();

    Map<Integer, Integer> getPoints();

    List<Card> getAvailableCards();

    List<Card> getTrashCards();

    Board getBoard();

}
