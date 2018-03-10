package com.pwr.game.engine

import com.pwr.game.engine.model.Turtle
import spock.lang.Specification
import spock.lang.Unroll

class GameImplNewGameTest extends Specification {

    Game game;

    void setup() {
        game = new GameImpl(["Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"])
    }

    @Unroll
    def "checks if player number #playerIndex have expected id, name and turtle"() {
        given:
        game.newGame()

        expect:
        id == game.getPlayers().get(id).getId()
        name == game.getPlayers().get(id).getName()
        turtle == game.getPlayers().get(id).getTurtle()

        where:
        id | name               | turtle
        0  | "Pinky Pie"        | Turtle.YELLOW
        1  | "Rainbow Dash"     | Turtle.BLUE
        2  | "Twigligh Sparkle" | Turtle.RED
        3  | "Apple Jack"       | Turtle.GREEN
        4  | "Rarity"           | Turtle.PURPLE
    }
}
