package com.pwr.game.engine;

import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class GameImpl implements Game {

    public static final int FIELDS_NUMBER = 8;

    private List<Player> players;

    private Map<Integer, Integer> points;

    private List<Card> availableCards;

    private List<Card> trashCards;

    private Board board;

    // todo lista z kolejnością graczy i ich losowanie

    public GameImpl() {
        newGame();
        points = new HashMap<>();
        players.forEach(p -> points.put(p.getId(), 0));
    }

    private List<List<Turtle>> createFields() {
        // todo zrobić fixed size list ograniczone FIELDS_NUMBER
        List<List<Turtle>> fields = new ArrayList<>(FIELDS_NUMBER);
        fields.set(0, Arrays.asList(Turtle.values()));
        return fields;
    }

    /**
     * Tworzy talię 40 kart
     */
    private void createAvailableCards() {
        List<Card> cards = createCards();
        availableCards.addAll(cards);
        availableCards.addAll(cards);
    }

    /**
     * @return lista kart z każdym żółwiem i z każdą z liczb: -2, -1, 1, 2
     */
    private List<Card> createCards() {
        return IntStream.range(-2, 3)
                .boxed()
                .flatMap(i -> Arrays.stream(Turtle.values())
                        .map(t -> new Card(t, i)))
                .collect(Collectors.toList());
    }

    private List<Player> createPlayers() {
        return IntStream.range(0, Turtle.values().length)
                .boxed()
                .map(i -> new Player(i, Turtle.values()[i]))
                .collect(Collectors.toList());
    }

    @Override
    public void newGame() {
        players = createPlayers();
        createAvailableCards();
        trashCards = new ArrayList<>();
        board = new Board(createFields());

        Collections.shuffle(availableCards);            // miesza karty
        players.forEach(p -> p.setCards(getDeck()));    // daje po 5 kart do łapki gracza
    }

    private List<Card> getDeck() {
        List<Card> sublist = availableCards.subList(0, 5);
        List<Card> deck = new ArrayList<>(sublist);
        sublist.clear();
        return deck;
    }


    @Override
    public void resetGame() {
        // todo
    }

    @Override
    public Player newRound() {
        // todo
        return null;
    }

    @Override
    public Board makeMove(Player player, Card card) {
        // todo
        return null;
    }
}
