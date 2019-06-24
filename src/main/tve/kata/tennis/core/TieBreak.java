package src.main.tve.kata.tennis.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TieBreak class to manage TieBreak
 */
public class TieBreak {

    // This map will manage Player and their score
    private Map<Player, Integer> ties = new HashMap<>();

    //Players
    private Player playerOne, playerTwo;

    // Constructor
    public TieBreak(Player playerOne, Player playerTwo, Map<Player, Integer> ties) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.ties = ties;
    }

    /**
     * Start the tieBreak
     *
     * @return TieBreak score
     */
    public Map<Player, Integer> run() {
        Player player = null;
        while (!isWinnerTie()) {
            changeScore(getPointWinner());
            checkWinner();
        }
        return ties;
    }

    /**
     * Check if there is a winner
     *
     * @return true if there is a winner
     */
    public boolean isWinnerTie() {
        return playerOne.isWinnerTie() || playerTwo.isWinnerTie();
    }

    /**
     * Get the winner player
     *
     * @return the player who win
     */
    public Player getWinnerTie() {
        return playerOne.isWinnerTie() ? playerOne : playerTwo;
    }

    /**
     * Determine witch player win this point
     *
     * @return point winner
     */
    public Player getPointWinner() {
        return new Random().nextInt(2) + 1 == 1 ? this.playerOne : this.playerTwo;
    }

    /**
     * Change score
     *
     * @param player the player who win the point
     */
    public void changeScore(Player player) {
        ties.put(player, ties.get(player) + 1);
    }

    /**
     * Check if here a winner
     */
    public void checkWinner() {
        int playerOneScore = ties.get(playerOne).intValue();
        int playerTwoScore = ties.get(playerTwo).intValue();
        if (playerOneScore >= 7 && (playerOneScore - playerTwoScore) >= 2) {
            playerOne.setWinnerTie(true);
        } else if (playerTwoScore >= 7 && (playerTwoScore - playerOneScore) >= 2) {
            playerTwo.setWinnerTie(true);
        }
    }

    //GETTERS && SETTERS

    public Map<Player, Integer> getTies() {
        return ties;
    }

    public void setTies(Map<Player, Integer> ties) {
        this.ties = ties;
    }


}
