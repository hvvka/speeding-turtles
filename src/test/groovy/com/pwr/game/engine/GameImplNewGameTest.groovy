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
    def "player #id has expected id=#id, name=#name and turtle=#turtle"() {
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

    @Unroll
    def "player #id has #numberOfCards cards in hand"() {
        given:
        game.newGame()

        expect:
        numberOfCards == game.getPlayers().get(id).getCards().size()

        where:
        id | numberOfCards
        0  | 5
        1  | 5
        2  | 5
        3  | 5
        4  | 5
    }

    @Unroll
    def "player #id has #points points"() {
        given:
        game.newGame()

        expect:
        points == game.getPoints().get(id)

        where:
        id | points
        0  | 0
        1  | 0
        2  | 0
        3  | 0
        4  | 0
    }

    @Unroll
    def "trash cards are empty"() {
        given:
        def expectedCards = []

        when:
        game.newGame()

        then:
        expectedCards == game.getTrashCards()
    }

    @Unroll
    def "new board has all the turtles on the first field"() {
        given:
        def expectedBoard = [[Turtle.YELLOW, Turtle.BLUE, Turtle.RED, Turtle.GREEN, Turtle.PURPLE]]

        when:
        game.newGame()

        then:
        expectedBoard == game.getBoard().getFields()
    }
}
