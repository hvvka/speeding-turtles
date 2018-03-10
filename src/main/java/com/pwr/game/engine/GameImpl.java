package com.pwr.game.engine;

import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameImpl implements Game {

    public static final int FIELDS_NUMBER = 8;

    private List<Player> players;

    private Map<Integer, Integer> points;

    private List<Card> availableCards;

    private List<Card> trashCards;

    private Board board;

    private List<Integer> playingOrder;

    private int currentPlaymaker;

    public GameImpl(List<String> playerNames) {
        players = createPlayers(playerNames);
        points = new HashMap<>();
        players.forEach(p -> points.put(p.getId(), 0));
    }

    private List<List<Turtle>> createFields() {
        // todo zrobić fixed size list ograniczone FIELDS_NUMBER
        List<List<Turtle>> fields = new ArrayList<>(FIELDS_NUMBER);
        fields.add(0, Arrays.asList(Turtle.values()));
        return fields;
    }

    /**
     * Creates deck of 40 cards.
     */
    private List<Card> createAvailableCards() {
        List<Card> cards = createCards();
        return Stream.concat(cards.stream(), cards.stream())
                .collect(Collectors.toList());
    }

    /**
     * Returns 20 different cards of every possible card combination (turtle colour with a move).
     *
     * @return a list of cards with every turtle combined with each of the following numbers: -2, -1, 1, 2
     */
    private List<Card> createCards() {
        return IntStream.range(-2, 3)
                .filter(i -> i != 0)
                .boxed()
                .flatMap(i -> Arrays.stream(Turtle.values())
                        .map(t -> new Card(t, i)))
                .collect(Collectors.toList());
    }

    private List<Player> createPlayers(List<String> names) {
        return IntStream.range(0, Turtle.values().length)
                .boxed()
                .map(i -> new Player(i, names.get(i), Turtle.values()[i]))
                .collect(Collectors.toList());
    }

    @Override
    public Board newGame() {
        currentPlaymaker = 0;
        availableCards = createAvailableCards();
        trashCards = new ArrayList<>();
        board = new Board(createFields());
        playingOrder = createPlayingOrder();

        Collections.shuffle(availableCards);            // miesza karty
        players.forEach(p -> p.setCards(getDeck()));    // daje po 5 kart do łapki gracza

        return board;
    }

    private List<Card> getDeck() {
        List<Card> sublist = availableCards.subList(0, 5);
        List<Card> deck = new ArrayList<>(sublist);
        sublist.clear();
        return deck;
    }

    private List<Integer> createPlayingOrder() {
        List<Integer> newPlayingOrder = players.stream().map(Player::getId).collect(Collectors.toList());
        Collections.shuffle(newPlayingOrder);
        return newPlayingOrder;
    }

    @Override
    public Player newRound() {
        int playerIdForThisRound = playingOrder.get(currentPlaymaker);
        shuffleCardsIfNoneAreAvailable();
        getOneCard(players.get(playerIdForThisRound));
        currentPlaymaker++;

        return players.get(playerIdForThisRound);
    }

    private void shuffleCardsIfNoneAreAvailable() {
        if (availableCards.isEmpty()) {
            Collections.shuffle(trashCards);
            availableCards.addAll(trashCards);
        }
    }

    private void getOneCard(Player player) {
        player.getCards().add(availableCards.remove(0));
    }

    @Override
    public Board makeMove(Player player, Card card) {
        Turtle turleToBeMoved = card.getTurtle();
        int moveDistance = card.getMove();

        Optional<List<Turtle>> turtleCurrentField = getTurtleCurrentField(turleToBeMoved);
        int turtleCurrentFieldIndex = board.getFields().indexOf(turtleCurrentField);
        Optional<Integer> turtleCurrentIndex = getTurtleCurrentIndex(turleToBeMoved);

        if (turtleCurrentFieldIndex == 0) {
            // wykonaj ruch bez przesuwania żółwi
            moveTurtleFromStartField(turleToBeMoved, moveDistance, turtleCurrentFieldIndex);
        } else {
            // wykonaj ruch z przesuwaniem żółwi nad przesuwanym
            moveTurtleWithOtherTurtles(moveDistance, turtleCurrentFieldIndex, turtleCurrentIndex);
        }

        // utylizacja karty
        trashCards.add(card);
        player.getCards().remove(card);

        return board;
    }

    private void moveTurtleWithOtherTurtles(int moveDistance, int turtleCurrentFieldIndex, Optional<Integer> turtleCurrentIndex) {
        int lastTurtleIndex = board.getFields().get(turtleCurrentFieldIndex).size() - 1;
        List<Turtle> turtlesToBeMoved = board.getFields()
                .get(turtleCurrentFieldIndex)
                .subList(turtleCurrentIndex.get(), lastTurtleIndex);
        board.getFields().get(moveDistance).addAll(turtlesToBeMoved);
    }

    private void moveTurtleFromStartField(Turtle turleToBeMoved, int moveDistance, int turtleCurrentFieldIndex) {
        board.getFields().get(turtleCurrentFieldIndex).remove(turleToBeMoved);
        board.getFields().get(moveDistance).add(turleToBeMoved);
    }

    private Optional<Integer> getTurtleCurrentIndex(Turtle turleToBeMoved) {
        return board.getFields().stream()
                .map(field -> field.indexOf(turleToBeMoved))
                .filter(index -> index != -1)
                .findFirst();
    }

    private Optional<List<Turtle>> getTurtleCurrentField(Turtle turleToBeMoved) {
        return board.getFields().stream()
                .filter(field -> field.contains(turleToBeMoved))
                .findFirst();
    }

    @Override
    public Map<Integer, Integer> getPoints() {
        return points;
    }

}
