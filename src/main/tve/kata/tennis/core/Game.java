package src.main.tve.kata.tennis.core;

import java.util.Map;
import java.util.Random;

/**
 * Game class manage start of game, point and end game
 */
public class Game {

    // This map will manage Player and their score
    private Map<Player, Score> scores;

    // Players
    private Player playerOne, playerTwo;

    /**
     * Game constructor
     */
    public Game(Player playerOne, Player playerTwo, Map<Player, Score> scores){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.scores = scores;
    }

    /**
     * Determine witch player win this point
     * @return point winner
     */
    public Player getPointWinner() {
        return new Random().nextInt(2) + 1 == 1 ? this.playerOne : this.playerTwo;
    }

    /**
     * Check if there is a winner
     * @return true if there is a winner
     */
    public boolean isWinner(){
        return playerOne.isWinnerGame() || playerTwo.isWinnerGame();
    }

    /**
     * Get the winner player
     * @return the player who win
     */
    public Player getWinner(){
        return playerOne.isWinnerGame() ? playerOne : playerTwo;
    }

    /**
     * Change the score of the winning point player
     * @param player player who won the point
     */
    public void changeScore(Player player) {
        Score lastScore = scores.get(player);

        switch (lastScore) {
            case LOVE:
                scores.put(player, Score.FIFTEEN);
                break;
            case FIFTEEN:
                scores.put(player, Score.THIRTY);
                break;
            case THIRTY:
                if (isOpponentForty(player)) {
                    deuce();
                } else {
                    scores.put(player, Score.FORTY);
                }
                break;
            case FORTY:
                if (isOpposentAdvantage(player)) {
                    deuce();
                } else {
                    player.setWinnerGame(true);
                }
                break;
            case DEUCE:
                setPlayerAdvantage(player);
                break;
            case ADVANTAGE:
                player.setWinnerGame(true);
                break;
        }
    }

    /**
     * Test if the opponent has 40 points
     * @param player the player who scored
     * @return his opponent
     */
    private boolean isOpponentForty(Player player){
        if(player.equals(playerOne)){
            return scores.get(playerTwo).equals(Score.FORTY);
        }
        return scores.get(playerOne).equals(Score.FORTY);
    }

    /**
     * Test if the opponent has advantage
     * @param player the player who scored
     * @return his opponent
     */
    private boolean isOpposentAdvantage(Player player){
        if(player.equals(playerOne)){
            return scores.get(playerTwo).equals(Score.ADVANTAGE);
        }
        return scores.get(playerOne).equals(Score.ADVANTAGE);
    }

    /**
     * Set DEUCE score to both players
     */
    private void deuce() {
        scores.put(playerOne,Score.DEUCE);
        scores.put(playerTwo,Score.DEUCE);
    }

    /**
     * Manage ADVANTAGE situation in case of DEUCE
     * @param player the player who scored
     */
    private void setPlayerAdvantage(Player player){
        if(player.equals(playerOne)){
            scores.put(playerOne,Score.ADVANTAGE);
            scores.put(playerTwo,Score.FORTY);
        }else{
            scores.put(playerOne,Score.FORTY);
            scores.put(playerTwo,Score.ADVANTAGE);
        }
    }



    public Map<Player, Score> getScores() {
        return scores;
    }

    public void setScores(Map<Player, Score> scores) {
        this.scores = scores;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }


}
