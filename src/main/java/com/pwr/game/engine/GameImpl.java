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

        Collections.shuffle(availableCards);            // miesza karty
        players.forEach(p -> p.setCards(new ArrayList<>(getDeck())));    // daje po 5 kart do łapki gracza

        return board;
    }

    private List<Card> getDeck() {
        List<Card> sublist = availableCards.subList(0, 5);
        List<Card> deck = new ArrayList<>(sublist);
        System.out.println(deck.size());
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
        if (player.getCards().size() < 5)
            player.getCards().add(availableCards.remove(0));
    }

    @Override
    public Board makeMove(Card card) {
        if (!players.get(currentPlaymaker).getCards().contains(card)) {
            System.out.println("PLAYER DOESN'T HAVE SUCH CARD");   // to powinien by wyjątek
        }

        Turtle turleToBeMoved = card.getTurtle();
        int moveDistance = card.getMove();

        Optional<Integer> turtleCurrentFieldIndex = getTurtleCurrentFieldIndex(turleToBeMoved);

        if (turtleCurrentFieldIndex.get() + moveDistance < 0 || turtleCurrentFieldIndex.get() + moveDistance >= FIELDS_NUMBER) {
            System.out.println("FORBIDDEN MOVE");
            throwCard(card);
            return board;
        }

        Optional<Integer> turtleCurrentIndex = getTurtleCurrentIndex(turleToBeMoved);

        if (turtleCurrentFieldIndex.get() == 0) {
            // wykonaj ruch bez przesuwania żółwi
            moveTurtleFromStartField(turleToBeMoved, moveDistance, turtleCurrentFieldIndex.get());
        } else {
            // wykonaj ruch z przesuwaniem żółwi nad przesuwanym
            moveTurtleWithOtherTurtles(moveDistance, turtleCurrentFieldIndex.get(), turtleCurrentIndex);
        }

        throwCard(card);
        return board;
    }

    private Optional<Integer> getTurtleCurrentFieldIndex(Turtle turleToBeMoved) {
        return IntStream.range(0, board.getFields().size())
                .boxed()
                .filter(i -> board.getFields().get(i).indexOf(turleToBeMoved) != -1)
                .findFirst();
    }

    private void throwCard(Card card) {
        trashCards.add(card);
        players.get(currentPlaymaker).getCards().remove(card);  // fixme musi usuwać różne obiekty, ale o tych samych wartościach
    }

    private void moveTurtleWithOtherTurtles(int moveDistance, int turtleCurrentFieldIndex, Optional<Integer> turtleCurrentIndex) {
        int lastTurtleIndex = board.getFields().get(turtleCurrentFieldIndex).size() - 1;
        List<Turtle> turtlesToBeMoved = board.getFields()
                .get(turtleCurrentFieldIndex)
                .subList(turtleCurrentIndex.get(), lastTurtleIndex);
        board.getFields().get(moveDistance).addAll(turtlesToBeMoved);
    }

    private void moveTurtleFromStartField(Turtle turleToBeMoved, int moveDistance, int turtleCurrentFieldIndex) {
        List<Turtle> turtleCurrentField = board.getFields().get(turtleCurrentFieldIndex);
        turtleCurrentField.remove(turleToBeMoved);
        board.getFields().get(moveDistance).add(turleToBeMoved);
    }

    private Optional<Integer> getTurtleCurrentIndex(Turtle turleToBeMoved) {
        return board.getFields().stream()
                .map(field -> field.indexOf(turleToBeMoved))
                .filter(index -> index != -1)
                .findFirst();
    }

    @Override
    public Map<Integer, Integer> getPoints() {
        return points;
    }

}
