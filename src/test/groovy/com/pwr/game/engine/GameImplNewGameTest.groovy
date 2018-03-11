package com.pwr.game.engine

import com.pwr.game.engine.model.Board
import com.pwr.game.engine.model.Card
import com.pwr.game.engine.model.Player
import com.pwr.game.engine.model.Turtle
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Field

class GameImplNewGameTest extends Specification {

    Game game

    void setup() {
        game = new GameImpl(["Pinky Pie", "Rainbow Dash", "Twigligh Sparkle", "Apple Jack", "Rarity"])
    }

    @Unroll
    def "player #id has expected id=#id, name=#name and turtle=#turtle"() {
        given:
        game.newGame()
        Field field = game.getClass().getDeclaredField("players")
        field.setAccessible(true)
        List<Player> players = field.get(game) as List<Player>

        expect:
        id == players.get(id).getId()
        name == players.get(id).getName()
        turtle == players.get(id).getTurtle()

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
        Field field = game.getClass().getDeclaredField("players")
        field.setAccessible(true)
        List<Player> players = field.get(game) as List<Player>

        expect:
        numberOfCards == players.get(id).getCards().size()

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
        Field field = game.getClass().getDeclaredField("points")
        field.setAccessible(true)
        Map<Integer, Integer> points = field.get(game) as Map<Integer, Integer>

        expect:
        expectedPoints == points.get(id)

        where:
        id | expectedPoints
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
        Field field = game.getClass().getDeclaredField("trashCards")
        field.setAccessible(true)
        List<Card> trashCards = field.get(game) as List<Card>

        then:
        expectedCards == trashCards
    }

    @Unroll
    def "new board has all the turtles on the first field"() {
        given:
        def expectedBoard = [[Turtle.YELLOW, Turtle.BLUE, Turtle.RED, Turtle.GREEN, Turtle.PURPLE],
                             [], [], [], [], [], [], []]

        when:
        game.newGame()
        Field field = game.getClass().getDeclaredField("board")
        field.setAccessible(true)
        Board board = field.get(game) as Board

        then:
        expectedBoard == board.getFields()
    }
}
