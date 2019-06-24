package src.test.tve.kata.tennis;

import org.junit.Before;
import org.junit.Test;
import src.main.tve.kata.tennis.core.Game;
import src.main.tve.kata.tennis.core.Match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    private Game game;

    @Before
    public void init() {
        Match match = new Match("PlayerTest1", "PlayerTest2");
        game = match.getGame();
    }

    @Test
    public void GameInitTest() {
        assertEquals("PlayerTest1", game.getPlayerOne().getName());
        assertEquals("PlayerTest2", game.getPlayerTwo().getName());
        game.getScores().forEach((player, score) -> {
            assertEquals("0", score.toString());
        });

    }

    @Test
    public void PlayerOneScoreTest() {
        game.changeScore(game.getPlayerOne());
        assertEquals("15", game.getScores().get(game.getPlayerOne()).toString());
    }

    @Test
    public void PlayerOneWinTest() {
        game.changeScore(game.getPlayerOne());// 15-0
        game.changeScore(game.getPlayerOne());// 30-0
        game.changeScore(game.getPlayerOne());// 40-0
        game.changeScore(game.getPlayerOne());// PlayerOne Win
        assertTrue(game.getPlayerOne().isWinnerGame());
        assertEquals("PlayerTest1", game.getPlayerOne().getName());
    }

    @Test
    public void DeusAndAdvantageTest() {
        game.changeScore(game.getPlayerOne());// 15-0
        game.changeScore(game.getPlayerOne());// 30-0
        game.changeScore(game.getPlayerOne());// 40-0
        game.changeScore(game.getPlayerTwo());// 40-15
        game.changeScore(game.getPlayerTwo());// 40-30
        game.changeScore(game.getPlayerTwo());// 40-40 DEUS
        game.changeScore(game.getPlayerTwo());// ADV player 2
        game.changeScore(game.getPlayerOne());// DEUS
        game.changeScore(game.getPlayerOne());// ADV player 1
        game.changeScore(game.getPlayerOne());// player 1 win

        assertTrue(game.getPlayerOne().isWinnerGame());
        assertEquals("PlayerTest1", game.getPlayerOne().getName());
    }


}
