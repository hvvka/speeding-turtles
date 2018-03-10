package com.pwr.game.engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class GameImplTest {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(Arrays.asList("Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"));
    }

    @Test
    public void newRound() {
        // todo
    }

    @Test
    public void makeMove() {
        // todo
    }

}