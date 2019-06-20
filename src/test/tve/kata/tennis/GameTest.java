package test.tve.kata.tennis;

import org.junit.jupiter.api.Test;
import main.tve.kata.tennis.core.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameTest {

    Game game;

    @Before
    public void GameCreator() {
        game = new Game("PlayerTest1","PlayerTest2");
    }
    @Test
    public void GameInitTest(){
        assertEquals("PlayerTest1",game.getPlayerOne().getName());
        assertEquals("PlayerTest2",game.getPlayerTwo().getName());
        game.getScores().forEach((player, score) -> {
            assertEquals("0",score.toString());
        });

    }

   @Test
    public void PlayerOneScoreTest(){
        game.changeScore(game.getPlayerOne());
        assertEquals("15",game.getScores().get(game.getPlayerOne()).toString());
    }

    @Test
    public void PlayerOneWinTest(){
        game.changeScore(game.getPlayerOne());
        game.changeScore(game.getPlayerOne());
        game.changeScore(game.getPlayerOne());
        game.changeScore(game.getPlayerOne());
        assertTrue(game.getPlayerOne().isWinner());
        assertEquals("PlayerTest1", game.getPlayerOne().getName());
    }

}
