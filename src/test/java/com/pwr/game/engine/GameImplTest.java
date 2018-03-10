package com.pwr.game.engine;

import com.pwr.game.engine.model.Board;
import com.pwr.game.engine.model.Card;
import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// FIXME testy tutaj są okropne, ale pomagają w debuggowaniu
// TODO do utylizacji
public class GameImplTest {

    private Game game;

    private List<Integer> playingOrder;

    private List<Player> players;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        game = new GameImpl(Arrays.asList("Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"));
        game.newGame();

        Field playingOrderField = game.getClass().getDeclaredField("playingOrder");
        playingOrderField.setAccessible(true);
        playingOrder = (List<Integer>) playingOrderField.get(game);

        Field playersField = game.getClass().getDeclaredField("players");
        playersField.setAccessible(true);
        players = (List<Player>) playersField.get(game);
    }

    @Test
    public void makeMove() {
        // given
        Card card = new Card(players.get(playingOrder.get(0)).getCards().get(0).getTurtle(),
                players.get(playingOrder.get(0)).getCards().get(0).getMove());
        List<List<Turtle>> fields = new ArrayList<>(GameImpl.FIELDS_NUMBER);
        for (int i = 0; i < GameImpl.FIELDS_NUMBER; i++) {
            fields.add(i, Collections.emptyList());
        }
        if (card.getMove() > 0) {
            fields.set(0, Arrays.stream(Turtle.values())
                    .filter(t -> t != card.getTurtle())
                    .collect(Collectors.toList()));
            fields.set(card.getMove(), new ArrayList<>(Collections.singletonList(card.getTurtle())));
        } else fields.set(0, new ArrayList<>(Arrays.asList(Turtle.values())));
        Board expectedBoard = new Board(fields);

        // when
        Board actualBoard = game.makeMove(card);

        // then
        Assert.assertEquals(expectedBoard, actualBoard);
    }

    @Test
    public void newRound() {
        // given
        game.makeMove(players.get(playingOrder.get(0)).getCards().get(0));
        Player expectedPlayer = new Player(players.get(playingOrder.get(0)).getId(),
                players.get(playingOrder.get(0)).getName(), players.get(playingOrder.get(0)).getTurtle());
        expectedPlayer.setCards(new ArrayList<>(players.get(playingOrder.get(0)).getCards()));

        // when
        Player actualPlayer = game.newRound();

        // then
        Assert.assertEquals(expectedPlayer.getId(), actualPlayer.getId());
        Assert.assertEquals(expectedPlayer.getTurtle(), actualPlayer.getTurtle());
        Assert.assertEquals(expectedPlayer.getName(), actualPlayer.getName());
        Assert.assertEquals(expectedPlayer.getCards().size(), actualPlayer.getCards().size());
//        Assert.assertNotEquals(expectedPlayer.getCards(), actualPlayer.getCards());
    }

}