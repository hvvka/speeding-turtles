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

    public List<Player> getPlayers();

    public Map<Integer, Integer> getPoints();

    public List<Card> getAvailableCards();

    public List<Card> getTrashCards();

    public Board getBoard();

}
