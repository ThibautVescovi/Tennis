package src.test.tve.kata.tennis;

import src.main.tve.kata.tennis.core.Game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void GameInitTest(){
        Game game = new Game("PlayerTest1","PlayerTest2");
        assertEquals("PlayerTest1",game.getPlayerOne().getName());
        assertEquals("PlayerTest2",game.getPlayerTwo().getName());
        game.getScores().forEach((player, score) -> {
            assertEquals("0",score.toString());
        });

    }

   @Test
    public void PlayerOneScoreTest(){
       Game game = new Game("PlayerTest1","PlayerTest2");
        game.changeScore(game.getPlayerOne());
        assertEquals("15",game.getScores().get(game.getPlayerOne()).toString());
    }

    @Test
    public void PlayerOneWinTest(){
        Game game = new Game("PlayerTest1","PlayerTest2");
        game.changeScore(game.getPlayerOne());// 15-0
        game.changeScore(game.getPlayerOne());// 30-0
        game.changeScore(game.getPlayerOne());// 40-0
        game.changeScore(game.getPlayerOne());// PlayerOne Win
        assertTrue(game.getPlayerOne().isWinner());
        assertEquals("PlayerTest1", game.getPlayerOne().getName());
    }

    @Test
    public void DeusAndAdvantageTest(){
        Game game = new Game("PlayerTest1","PlayerTest2");
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

        assertTrue(game.getPlayerOne().isWinner());
        assertEquals("PlayerTest1", game.getPlayerOne().getName());
    }

}
