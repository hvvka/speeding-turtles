package com.pwr.game.engine;

import com.pwr.game.engine.model.Player;
import com.pwr.game.engine.model.Turtle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class GameImplTest {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(Arrays.asList("Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"));
    }

    // todo delete this test
    @Test
    public void newGame() {
        // given
        List<Player> expectedPlayers = getExpectedPlayers();
        int index = 1;

        // when
        game.newGame();

        // then
        Assert.assertEquals(expectedPlayers.get(index).getId(), game.getPlayers().get(index).getId());
        Assert.assertEquals(expectedPlayers.get(index).getName(), game.getPlayers().get(index).getName());
        Assert.assertEquals(expectedPlayers.get(index).getTurtle(), game.getPlayers().get(index).getTurtle());
    }

    @Test
    public void resetGame() {
    }

    @Test
    public void newRound() {
    }

    @Test
    public void makeMove() {
    }

    private List<Player> getExpectedPlayers() {
        return Arrays.asList(new Player(0, "Pinkie Pie", Turtle.YELLOW),
                new Player(1, "Rainbow Dash", Turtle.BLUE), new Player(2, "Twigligh Sparkle", Turtle.RED),
                new Player(3, "Apple Jack", Turtle.GREEN), new Player(4, "Rarity", Turtle.PURPLE));
    }
}