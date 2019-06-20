package main.tve.kata.tennis.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Game class manage start of game, point and end game
 */
public class Game {

    // This map will manage Player and their score
    private Map<Player, Score> scores = new HashMap<>();

    private Player playerOne, playerTwo;

    /**
     * Game constructor, init player and score
     * @param playerNameOne
     * @param playerNameTwo
     */
    public Game(String playerNameOne,String playerNameTwo){
        initMatch(playerNameOne, playerNameTwo);
    }

    /**
     * Tennis Game Manager
     */
    public void run(){
        while (!isWinner()){
            displayScore();
            changeScore(getPointWinner());
        }

        System.out.println("And the winner iiiiiiiiiiis : " + getWinner());
    }
    /**
     * Init score map with player and score set to 0
     * @param playerNameOne player one name
     * @param playerNameTwo player two name
     */
    private void initMatch(String playerNameOne, String playerNameTwo){
        playerOne = new Player(playerNameOne);
        playerTwo = new Player(playerNameTwo);
        scores.put(playerOne,Score.LOVE);
        scores.put(playerTwo,Score.LOVE);
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
     * @return
     */
    private boolean isWinner(){
        return playerOne.isWinner() || playerTwo.isWinner();
    }

    private String getWinner(){
        return playerOne.isWinner() ? playerOne.getName() : playerTwo.getName();
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
                scores.put(player, Score.FORTY);
                break;
            case FORTY:
                player.setWinner(true);
                break;
        }
    }

    /**
     * Display current score
     */
    private void displayScore(){

        scores.forEach((player, score) -> {
            System.out.println(player + "[" + score + "]");

        });
        System.out.println(" ------- ");
    }

    public Map<Player, Score> getScores() {
        return scores;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }


}
