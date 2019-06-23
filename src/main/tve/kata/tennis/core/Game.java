package src.main.tve.kata.tennis.core;

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
                    player.setWinner(true);
                }
                break;
            case DEUCE:
                setPlayerAdvantage(player);
                break;
            case ADVANTAGE:
                player.setWinner(true);
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
