package src.test.tve.kata.tennis;

import org.junit.Before;
import org.junit.Test;
import src.main.tve.kata.tennis.core.*;

import static org.junit.Assert.assertEquals;


public class TieBreakTest {

    private Game game;
    private Set set;
    private TieBreak tie;

    @Before
    public void init() {
        Match match = new Match("PlayerTest1", "PlayerTest2");
        game = match.getGame();
        set = match.getSet();
        match.initTie();
        tie = match.getTie();
    }

    @Test
    public void pointWinnerTest() {
        Player player = tie.getPointWinner();
        if (player.equals("PlayerTest1")) {
            assertEquals(Integer.valueOf(1), tie.getTies().get(game.getPlayerOne()));
            assertEquals(Integer.valueOf(0), tie.getTies().get(game.getPlayerTwo()));
        } else if (player.equals("PlayerTest2")) {
            assertEquals(Integer.valueOf(0), tie.getTies().get(game.getPlayerOne()));
            assertEquals(Integer.valueOf(1), tie.getTies().get(game.getPlayerTwo()));
        }

    }

    @Test
    public void changeScoreTest() {
        tie.changeScore(game.getPlayerOne());
        assertEquals(Integer.valueOf(1), tie.getTies().get(game.getPlayerOne()));
    }

    @Test
    public void checkWinnerTest() {
        for (int i = 0; i < 7; i++) {
            tie.changeScore(game.getPlayerOne());
        }
        tie.checkWinner();
        assertEquals(true, tie.isWinnerTie());
        assertEquals("PlayerTest1", tie.getWinnerTie().getName());
    }

    @Test
    public void checkWinnerNotTwoPointTest() {
        for (int i = 0; i < 7; i++) {
            tie.changeScore(game.getPlayerOne());
            tie.changeScore(game.getPlayerTwo());
        }
        tie.checkWinner();
        assertEquals(false, tie.isWinnerTie());
    }

    @Test
    public void checkWinnerTwoPointTest() {
        for (int i = 0; i < 7; i++) {
            tie.changeScore(game.getPlayerOne());
            tie.changeScore(game.getPlayerTwo());
        } //7 - 7
        tie.changeScore(game.getPlayerTwo());// 7 - 8
        tie.changeScore(game.getPlayerTwo());// 7 - 9
        tie.checkWinner();
        assertEquals(true, tie.isWinnerTie());
        assertEquals("PlayerTest2", tie.getWinnerTie().getName());
    }

}
