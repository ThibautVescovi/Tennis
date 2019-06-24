package src.test.tve.kata.tennis;

import org.junit.Before;
import org.junit.Test;
import src.main.tve.kata.tennis.core.Game;
import src.main.tve.kata.tennis.core.Match;
import src.main.tve.kata.tennis.core.Set;

import static org.junit.Assert.assertEquals;


public class SetTest {

    private Game game;
    private Set set;

    @Before
    public void init() {
        Match match = new Match("PlayerTest1", "PlayerTest2");
        game = match.getGame();
        set = match.getSet();
    }

    @Test
    public void OpponentTest() {
        assertEquals("PlayerTest2", set.opponent(game.getPlayerOne()).getName());
    }

    @Test
    public void addPointTest() {
        set.addPoint(game.getPlayerOne());
        assertEquals(Integer.valueOf(1), set.getSets().get(game.getPlayerOne()));
    }

    @Test
    public void winnerTest() {
        for (int i = 0; i < 6; i++) {
            set.addPoint(game.getPlayerOne());
        }
        assertEquals(true, set.isWinnerSet());
        assertEquals("PlayerTest1", set.getWinnerSet().getName());
    }

}
