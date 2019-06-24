package src.main.tve.kata.tennis.core;

import java.util.HashMap;
import java.util.Map;

public class Match {

    private Player playerOne, playerTwo;

    // This map will manage Player and their score
    private Map<Player, Score> scores = new HashMap<>();

    private Map<Player, Integer> sets = new HashMap<>();

    private Map<Player, Integer> ties = new HashMap<>();

    private Game game;

    private Set set;

    private TieBreak tie;

    private boolean endMatch = false;

    public Match(String playerNameOne,String playerNameTwo){
        initMatch(playerNameOne, playerNameTwo);
    }

    /**
     * Init score map with player and score set to 0
     * @param playerNameOne player one name
     * @param playerNameTwo player two name
     */
    private void initMatch(String playerNameOne, String playerNameTwo){
        playerOne = new Player(playerNameOne);
        playerTwo = new Player(playerNameTwo);

        initGame();
        initSet();
        initTie();

        game = new Game(playerOne,playerTwo,scores);
        set = new Set(playerOne,playerTwo,sets);
    }

    /**
     * Tennis Game Manager
     */
    public void run(){
        while (!endMatch){
            System.out.println("Score : " + sets.get(playerOne) +" / " +  sets.get(playerTwo));
            displayGameScore();
            game.changeScore(game.getPointWinner());
            if(game.isWinner()){
                endMatch = set.addPoint(game.getWinner());
                initGame();
            }
        }

        System.out.println("And the winner iiiiiiiiiiis : " + set.getWinnerSet().getName() + " - Score final :  " + sets.get(playerOne) +" / " +  sets.get(playerTwo));
    }


    /**
     * Display current score
     */
    private void displayGameScore(){

        scores.forEach((player, score) -> {
            System.out.println(player + "[" + score + "]");

        });
        System.out.println(" ------- ");
    }

    /**
     * Init a GAME
     */
    public void initGame(){
        scores.put(playerOne,Score.LOVE);
        scores.put(playerTwo,Score.LOVE);
        playerOne.setWinnerGame(false);
        playerTwo.setWinnerGame(false);
    }

    /**
     * Init a SET
     */
    public void initSet(){
        sets.put(playerOne,0);
        sets.put(playerTwo,0);
    }

    /**
     * Init a TIE BREAK
     */
    public void initTie(){
        ties.put(playerOne,0);
        ties.put(playerTwo,0);
    }

    // GETTERS & SETTERS

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

}
