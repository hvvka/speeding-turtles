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

    private static final int PLAYERS_NUMBER = 5;

    private List<Player> players;

    private Map<Integer, Integer> points;

    private List<Card> availableCards;

    private List<Card> trashCards;

    private Board board;

    private List<Integer> playingOrder;

    private int currentPlaymaker;

    private int winnerId;

    public GameImpl(List<String> playerNames) {
        players = createPlayers(playerNames);
        points = new HashMap<>();
        players.forEach(p -> points.put(p.getId(), 0));
        winnerId = -1;
    }

    private List<List<Turtle>> createFields() {
        List<List<Turtle>> fields = new ArrayList<>(FIELDS_NUMBER);
        fields.add(0, new ArrayList<>(Arrays.asList(Turtle.values())));

        for (int i = 1; i < FIELDS_NUMBER; i++) {
            fields.add(i, new ArrayList<>());
        }
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

        Collections.shuffle(availableCards);
        players.forEach(p -> p.setCards(new ArrayList<>(getDeck())));

        return board;
    }

    private List<Card> getDeck() {
        List<Card> sublist = availableCards.subList(0, PLAYERS_NUMBER);
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

        return players.get(playerIdForThisRound);
    }

    private void shuffleCardsIfNoneAreAvailable() {
        if (availableCards.isEmpty()) {
            Collections.shuffle(trashCards);
            availableCards.addAll(trashCards);
        }
    }

    private void getOneCard(Player player) {
        if (player.getCards().size() < PLAYERS_NUMBER)
            player.getCards().add(availableCards.remove(0));
    }

    @Override
    public Board makeMove(Card card) {
        if (!players.get(playingOrder.get(currentPlaymaker)).getCards().contains(card)) {
            System.out.println("PLAYER DOESN'T HAVE SUCH CARD");   // it should be an exception
        }

        Turtle turtleToBeMoved = card.getTurtle();
        int moveDistance = card.getMove();
        int turtleCurrentFieldIndex = getTurtleCurrentFieldIndex(turtleToBeMoved);
        int turtleCurrentIndex = getTurtleCurrentIndex(turtleToBeMoved);

        if (turtleCurrentFieldIndex + moveDistance < 0) {
            System.out.println("FORBIDDEN MOVE");
        } else if (turtleCurrentFieldIndex == 0) {
            moveTurtleFromStartField(turtleToBeMoved, moveDistance);
        } else {
            moveTurtleWithOtherTurtles(moveDistance, turtleCurrentFieldIndex, turtleCurrentIndex);
        }

        throwCard(card);
        currentPlaymaker = (++currentPlaymaker) % PLAYERS_NUMBER;

        return board;
    }

    private int getTurtleCurrentFieldIndex(Turtle turtleToBeMoved) {
        return IntStream.range(0, board.getFields().size())
                .boxed()
                .filter(i -> board.getFields().get(i).indexOf(turtleToBeMoved) != -1)
                .findFirst().get();
    }

    private void throwCard(Card card) {
        trashCards.add(card);
        players.get(playingOrder.get(currentPlaymaker)).getCards().remove(card);
    }

    private void moveTurtleWithOtherTurtles(int moveDistance, int turtleCurrentFieldIndex, int turtleCurrentIndex) {
        int lastTurtleIndex = board.getFields().get(turtleCurrentFieldIndex).size() - 1;
        List<Turtle> turtlesToBeMoved = board.getFields()
                .get(turtleCurrentFieldIndex)
                .subList(turtleCurrentIndex, lastTurtleIndex + 1);
        if (turtleCurrentFieldIndex + moveDistance >= FIELDS_NUMBER - 1) {
            board.getFields().get(FIELDS_NUMBER - 1).addAll(turtlesToBeMoved);
            addWinnerPoints();
        } else {
            board.getFields().get(turtleCurrentFieldIndex + moveDistance).addAll(turtlesToBeMoved);
        }
        turtlesToBeMoved.clear();
    }

    private void addWinnerPoints() {
        Turtle winnerTurtle = board.getFields().get(FIELDS_NUMBER - 1).get(0);
        winnerId = players.stream()
                .filter(player -> player.getTurtle().equals(winnerTurtle))
                .findFirst().get()
                .getId();

        points.merge(winnerId, 1, (oldValue, one) -> oldValue + one);
    }

    private void moveTurtleFromStartField(Turtle turtleToBeMoved, int moveDistance) {
        board.getFields().get(0).remove(turtleToBeMoved);
        board.getFields().get(moveDistance).add(turtleToBeMoved);
    }

    private int getTurtleCurrentIndex(Turtle turtleToBeMoved) {
        return board.getFields().stream()
                .map(field -> field.indexOf(turtleToBeMoved))
                .filter(index -> index != -1)
                .findFirst().get();
    }

    @Override
    public Map<Player, Integer> getResult() {
        Map<Player, Integer> playersResult = new HashMap<>();
        for (int i = 0; i < PLAYERS_NUMBER; i++)
            playersResult.put(players.get(i), points.get(i));

        return playersResult;
    }

    @Override
    public Player winGame() {
        return winnerId != -1 ? players.get(winnerId) : new Player(-1, "noone", Turtle.BLUE);
    }
}
